package nl.djja.rpi.temperaturesensorservice.factories;

import nl.djja.rpi.temperaturesensorservice.services.TemperatureSensorService;
import nl.djja.rpi.temperaturesensorservice.services.TemperatureSensorServiceImpl;

public class ServiceFactory {
    public static TemperatureSensorService getTemperatureSensorService() {
        return new TemperatureSensorServiceImpl();
    }
}
