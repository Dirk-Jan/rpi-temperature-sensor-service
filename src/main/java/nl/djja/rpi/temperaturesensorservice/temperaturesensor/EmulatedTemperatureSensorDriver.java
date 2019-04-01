package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;

import java.util.HashMap;

public class EmulatedTemperatureSensorDriver {
    private static HashMap<String, FictionalTemperatureSensor> temperatureSensors = new HashMap<>();

    public static void addTemperatureSensor(FictionalTemperatureSensor temperatureSensor) { temperatureSensors.put(temperatureSensor.getSerial(), temperatureSensor); }

    public static FictionalTemperatureSensor getTemperatureSensorBySerial(String serial) { return temperatureSensors.get(serial); }

    public static void setTemperature(String serial, float value) throws ItemNotFoundException {
        FictionalTemperatureSensor temperatureSensor = temperatureSensors.get(serial);
        if (temperatureSensor == null) throw new ItemNotFoundException("FictionalTemperatureSensor with serial " + serial + " was not found.");
        temperatureSensor.setTemperature(value);
    }
}
