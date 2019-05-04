package nl.djja.rpi.temperaturesensorservice.services;

import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;
import nl.djja.rpi.temperaturesensorservice.factories.TemperatureSensorFactory;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.FictionalTemperatureSensor;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.TemperatureSensor;

public class TemperatureSensorServiceImpl implements TemperatureSensorService {


    @Override
    public float getTemperature(String serial) throws TemperatureReadingException, ItemNotFoundException {
        return TemperatureSensorFactory.getTemperatureSensor(serial).readTemperature();
    }

    @Override
    public void setTemperature(String serial, float value) {
        TemperatureSensor temperatureSensor = TemperatureSensorFactory.getTemperatureSensor(serial);
        FictionalTemperatureSensor fictionalTemperatureSensor = (FictionalTemperatureSensor)temperatureSensor;
        // TODO Catch error
        fictionalTemperatureSensor.setTemperature(value);
    }
}
