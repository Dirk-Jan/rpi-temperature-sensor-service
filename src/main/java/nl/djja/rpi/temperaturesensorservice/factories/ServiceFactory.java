package nl.djja.rpi.temperaturesensorservice.factories;

import nl.djja.rpi.temperaturesensorservice.AppConfig;
import nl.djja.rpi.temperaturesensorservice.services.TemperatureSensorService;
import nl.djja.rpi.temperaturesensorservice.services.TemperatureSensorServiceReadFromMemoryImpl;
import nl.djja.rpi.temperaturesensorservice.services.TemperatureSensorServiceReadFromSensorImpl;

public class ServiceFactory {
    public static TemperatureSensorService getTemperatureSensorService() {
        if (AppConfig.mockTemperatureSensor)
            return new TemperatureSensorServiceReadFromSensorImpl();

        return new TemperatureSensorServiceReadFromMemoryImpl();
    }
}
