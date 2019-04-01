package nl.djja.rpi.temperaturesensorservice.services;

public interface TemperatureSensorService {
    float getTemperature(String serial);
    void setTemperature(String serial, float value);
}
