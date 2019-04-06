package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;

public interface TemperatureSensor {
    float readTemperature() throws TemperatureReadingException, ItemNotFoundException;
    String getSerial();
}