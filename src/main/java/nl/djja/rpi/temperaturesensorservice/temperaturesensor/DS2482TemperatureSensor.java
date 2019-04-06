package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DS2482TemperatureSensor implements TemperatureSensor {
    private String serial;

    public DS2482TemperatureSensor(String serial) {
        this.serial = serial;
    }

    @Override
    public float readTemperature() throws TemperatureReadingException, ItemNotFoundException {
        float value;

        String filename = "/mnt/1wire/28.FF" + serial + "/temperature";
//        String filename = "/Users/dirk-jan/Documents/deleteme/28.FF" + serial + "/temperature";
        try (
            FileInputStream file = new FileInputStream(filename)
        ) {
            byte[] bytes = new byte[4];
            file.read(bytes, 0, 4);
            value = Float.valueOf(new String(bytes));
        } catch (FileNotFoundException e) {
            throw new ItemNotFoundException("Temperature sensor with serial '" + serial + "' is not registered.");
        } catch (IOException e) {
            throw new TemperatureReadingException("Could not read value from '" + filename + "'");
        }

        return value;
    }

    @Override
    public String getSerial() {
        return serial;
    }
}
