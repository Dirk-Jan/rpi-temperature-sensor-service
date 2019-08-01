package nl.djja.rpi.temperaturesensorservice;

import nl.djja.rpi.temperaturesensorservice.factories.ServiceFactory;
import nl.djja.rpi.temperaturesensorservice.rest.RESTEmbeddedJetty;
import nl.djja.rpi.temperaturesensorservice.services.TemperatureSensorService;

public class ServiceInitializer {
    private static Thread temperatureReadingThread = null;
    private static Thread communicationThread = null;

    public static void main(String[] args) {
        AppConfig.mockTemperatureSensor = true;

        TemperatureSensorService temperatureSensorService = ServiceFactory.getTemperatureSensorService();
        if (temperatureSensorService instanceof Runnable) {
            Runnable runnable = (Runnable) temperatureSensorService;
            temperatureReadingThread =  new Thread(runnable);
            temperatureReadingThread.start();
        }

        RESTEmbeddedJetty restEmbeddedJetty = new RESTEmbeddedJetty();
        communicationThread = new Thread(restEmbeddedJetty);
        communicationThread.start();

        try {
            communicationThread.join();
            if (temperatureReadingThread != null) {
                temperatureReadingThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
