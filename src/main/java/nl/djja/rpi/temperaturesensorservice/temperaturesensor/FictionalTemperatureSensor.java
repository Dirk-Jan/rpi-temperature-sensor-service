package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

public class FictionalTemperatureSensor implements TemperatureSensor{
    private float temperature;
    private String serial;

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    public float readTemperature() {
        return temperature;
    }

    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }

    public FictionalTemperatureSensor(float temperature) {
        this.temperature = temperature;
    }

    public FictionalTemperatureSensor(String serial) {
        this.temperature = 2;
        this.serial = serial;
    }
}
