package nl.djja.rpi.temperaturesensorservice;

import nl.djja.rpi.temperaturesensorservice.factories.ServiceFactory;
import nl.djja.rpi.temperaturesensorservice.rest.RESTEmbeddedJetty;
import nl.djja.rpi.temperaturesensorservice.services.TemperatureSensorService;

public class ServiceInitializer {
    public static void main(String[] args) {
//        AppConfig.mockTemperatureSensor = true;

        TemperatureSensorService temperatureSensorService = ServiceFactory.getTemperatureSensorService();
        if (temperatureSensorService instanceof Runnable) {
            Runnable runnable = (Runnable) temperatureSensorService;
            new Thread(runnable).start();
        }

        RESTEmbeddedJetty restEmbeddedJetty = new RESTEmbeddedJetty();
        restEmbeddedJetty.run();

        // TODO stop the application
    }
}
