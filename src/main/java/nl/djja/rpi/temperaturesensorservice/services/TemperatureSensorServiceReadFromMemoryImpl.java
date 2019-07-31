package nl.djja.rpi.temperaturesensorservice.services;

import nl.djja.rpi.temperaturesensorservice.AppConfig;
import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;
import nl.djja.rpi.temperaturesensorservice.factories.TemperatureSensorFactory;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.DS2482TemperatureSensor;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.FictionalTemperatureSensor;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.TemperatureSensor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemperatureSensorServiceReadFromMemoryImpl implements TemperatureSensorService, Runnable {

    private static volatile HashMap<String, Float> readings;
    private volatile boolean cancelled;

    @Override
    public float getTemperature(String serial) throws TemperatureReadingException, ItemNotFoundException {
//        synchronized (this) {
            return readings.get(serial);
//        }
    }

    @Override
    public void setTemperature(String serial, float value) {
//        synchronized (this) {
            readings.put(serial, value);
//        }
    }

    public void cancel(){
        cancelled = true;
    }

    public void run() {
        cancelled = false;

        init();

        while(!cancelled){
            for(Map.Entry<String, Float> map : readings.entrySet()){
                try {
                    setTemperature(map.getKey(), TemperatureSensorFactory.getTemperatureSensor(map.getKey()).readTemperature());
                } catch (TemperatureReadingException e) {
                    e.printStackTrace();
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(AppConfig.temperatureSensorValueUpdateInterval);
            } catch (InterruptedException e) {
//                Logger.printThrowable(e);
            }
        }
    }

    private void init() {
        readings = new HashMap<>();
        Collection<String> serialNumbers = TemperatureSensorFactory.getTemperatureSensor("").getConnectedTemperatureSensors();
        for (String serialNumber : serialNumbers) {
            readings.put(serialNumber, -3f);
        }
    }
}
