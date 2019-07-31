package nl.djja.rpi.temperaturesensorservice.services;

import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;
import nl.djja.rpi.temperaturesensorservice.factories.TemperatureSensorFactory;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.DS2482TemperatureSensor;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.FictionalTemperatureSensor;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.TemperatureSensor;

import java.util.HashMap;
import java.util.Map;

public class TemperatureSensorServiceReadFromSensorImpl implements TemperatureSensorService {

    @Override
    public float getTemperature(String serial) throws TemperatureReadingException, ItemNotFoundException {
        return TemperatureSensorFactory.getTemperatureSensor(serial).readTemperature();
    }

    @Override
    public void setTemperature(String serial, float value) throws Exception {
        TemperatureSensor temperatureSensor = TemperatureSensorFactory.getTemperatureSensor(serial);

        if (temperatureSensor instanceof FictionalTemperatureSensor) {
            ((FictionalTemperatureSensor)temperatureSensor).setTemperature(value);
        } else {
            // TODO error
            throw new Exception("This type of temperature sensor does not allow its temperature to be set.");
        }
    }
}
