package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

public interface TemperatureSensor {
    float readTemperature();
    String getSerial();
}