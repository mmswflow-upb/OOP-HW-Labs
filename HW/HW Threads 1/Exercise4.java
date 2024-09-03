import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Exercise4 {

    private static Path testPath = Paths.get("TestFolder");
    private static Path PATH = Paths.get("TextFiles");

    public static void main(String[] args) {

        Path mainThreadPath = testPath;
        Path parallelThreadsPath = testPath;

        // Using only main thread

        try {
            Map<String, Integer> wordCount = new TreeMap<>();
            Stream<Path> paths;

            paths = Files.list(mainThreadPath);

            Long tStart = System.nanoTime();
            findWordCount(paths, wordCount);
            wordCount.forEach((word, count) -> {

                System.out.println(word + " : " + count + " times");
            });
            System.out.println(
                    "\nTime it took to run using main thread only: " + ((System.nanoTime() - tStart) / 1_000_000.0)
                            + " ms\n");

        } catch (IOException e) {

            e.printStackTrace();
        }

        // using multiple parallel threads

        ExecutorService executor = Executors.newFixedThreadPool(10);
        ArrayList<Future<TreeMap<String, Integer>>> listOfFutures = new ArrayList<>();
        ArrayList<TreeMap<String, Integer>> listOfWordCountMaps = new ArrayList<>();
        Long tStart_Parallel = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            int n = i;

            listOfFutures.add(executor.submit(new Callable<TreeMap<String, Integer>>() {

                public TreeMap<String, Integer> call() {

                    TreeMap<String, Integer> tempWordCount = new TreeMap<>();

                    try {
                        // I want each thread to read the 10 files coming after the last 10 files which
                        // were read, (We skip 10 files each time depending on the current iteration )
                        Stream<Path> paths = Files.list(parallelThreadsPath).skip(n * 10).limit(10);
                        findWordCount(paths, tempWordCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return tempWordCount;
                }
            }));

        }

        executor.shutdown();

        for (Future future : listOfFutures) {

            try {
                listOfWordCountMaps.add((TreeMap<String, Integer>) future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        mergeMaps(listOfWordCountMaps);
        System.out.println("\nTime it took to run using parallel threads "
                + ((System.nanoTime() - tStart_Parallel) / 1_000_000.0) + " ms\n");

    }

    private static void mergeMaps(List<TreeMap<String, Integer>> listOfMaps) {

        Map<String, Integer> finalParallelMap = (TreeMap<String, Integer>) listOfMaps.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Key Mapper
                        Map.Entry::getValue, // Value Mapper
                        (value1, value2) -> value1 + value2, // Merge Function: Sum values if key is duplicated
                        TreeMap::new // Map Supplier: TreeMap for sorted keys
                ));

        finalParallelMap.forEach((word, count) -> {

            System.out.println(word + " : " + count + " times.");
        });
    }

    private static void findWordCount(Stream<Path> paths, Map<String, Integer> wordCount) {

        paths.forEach(path -> {

            ArrayList<String> lines;
            try {
                lines = (ArrayList<String>) Files.readAllLines(path);

                // Get all the lines, split them into a list of Strings, make all the words have
                // lower case, collect them into a list of strings.
                ArrayList<String> words = (ArrayList<String>) lines.stream()
                        .flatMap(line -> Arrays.stream(line.split("\\s+"))).map(word -> word.toLowerCase())
                        .collect(Collectors.toList());

                updateWordCount(wordCount, words);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    private static void updateWordCount(Map<String, Integer> wordCountMap, List<String> wordsList) {

        for (String word : wordsList) {

            Integer oldCount = wordCountMap.get(word);
            wordCountMap.put(word, oldCount == null ? 1 : 1 + oldCount);
        }
    }
}