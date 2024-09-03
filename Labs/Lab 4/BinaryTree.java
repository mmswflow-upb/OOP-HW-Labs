import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public void add(T val) {

        root = insertRec(root, val);

    }

    private Node<T> insertRec(Node<T> root, T val) {

        if (root == null) {

            return new Node(val);

        }
        if (val.compareTo(root.val) < 0) {// Check if its smaller

            root.left = insertRec(root.left, val);

        } else if (val.compareTo(root.val) == 0) {// Check if they're equal, we won't add duplicates
            return root;
        } else {// Check if it's bigger
            root.right = insertRec(root.right, val);

        }

        return root;
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node curr) {

        if (curr == null) {
            return;
        }

        if (curr.left != null) {
            inOrderRec(curr.left);
        }
        System.out.print(curr.val + " ");
        if (curr.right != null) {
            inOrderRec(curr.right);
        }
    }

    private static class Node<T> {
        T val;
        Node<T> left, right;

        public Node(T val) {
            this.val = val;
        }

        public String toString() {
            return this.val.toString();
        }
    }

}