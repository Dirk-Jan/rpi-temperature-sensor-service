package nl.djja.rpi.temperaturesensorservice.factories;

import nl.djja.rpi.temperaturesensorservice.AppConfig;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.DS2482TemperatureSensor;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.FictionalTemperatureSensor;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.TemperatureSensor;

public class TemperatureSensorFactory {
    public static TemperatureSensor getTemperatureSensor(String serial) {
        if (AppConfig.mockTemperatureSensor)
            return new FictionalTemperatureSensor(serial);

        return new DS2482TemperatureSensor(serial);
    }
}
