public class SensorEvent {

    private int sourceId;
    private String type;
    private Double time;// in seconds

    SensorEvent(int id, String ty, Double ti) {
        sourceId = id;
        type = ty;
        time = ti;
    }

    public int getSourcerId() {
        return sourceId;
    }

    public String getType() {
        return type;
    }

    public Double getTime() {
        return time;
    }

    public String toString() {
        return "Sensor ID: " + sourceId + " | Type: " + type + " | Time: " + time;
    }
}
