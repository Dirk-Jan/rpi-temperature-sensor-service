package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FictionalTemperatureSensor implements TemperatureSensor {
    private static HashMap<String, Float> temperatureValues = new HashMap<String, Float>();
    private String serial;

    public void setTemperature(float temperature) {
        temperatureValues.put(serial, temperature);
    }
    public float readTemperature() {
        return temperatureValues.get(serial);
    }

    public String getSerial() { return serial; }

    @Override
    public Collection<String> getConnectedTemperatureSensors() {
        return new ArrayList<>();
    }

    public FictionalTemperatureSensor(String serial) {
        this.serial = serial;
        Float temperature = temperatureValues.get(serial);
        if (temperature == null) {
            temperatureValues.put(serial, 2f);
        }
    }
}
