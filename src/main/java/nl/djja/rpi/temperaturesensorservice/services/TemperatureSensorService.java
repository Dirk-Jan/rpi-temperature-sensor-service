package nl.djja.rpi.temperaturesensorservice.services;

import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;

public interface TemperatureSensorService {
    float getTemperature(String serial) throws TemperatureReadingException, ItemNotFoundException;
    void setTemperature(String serial, float value);
}
