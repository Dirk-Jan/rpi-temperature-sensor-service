package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;

import java.util.Collection;

public interface TemperatureSensor {
    float readTemperature() throws TemperatureReadingException, ItemNotFoundException;
    String getSerial();

    Collection<String> getConnectedTemperatureSensors();
}