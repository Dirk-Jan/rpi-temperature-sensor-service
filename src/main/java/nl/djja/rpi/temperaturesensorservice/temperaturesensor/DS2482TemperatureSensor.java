package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DS2482TemperatureSensor implements TemperatureSensor {
    private String serialNumber;

    public DS2482TemperatureSensor(String serial) {
        this.serialNumber = serialNumber;
    }

    @Override
    public float readTemperature() throws TemperatureReadingException, ItemNotFoundException {
        float value = -1;

        try (
                FileInputStream file = new FileInputStream("/mnt/1wire/28.FF" + serialNumber + "/temperature")
        ) {
            byte[] bytes = new byte[4];
            file.read(bytes, 0, 4);
            value = Float.valueOf(new String(bytes));
        } catch (FileNotFoundException e) {
//            Logger.printThrowable(e);
        } catch (IOException e) {
//            Logger.printThrowable(e);
        }

        return value;
    }

    @Override
    public String getSerial() {
        return serialNumber;
    }

    public Collection<String> getConnectedTemperatureSensors() {
        List<String> serialNumbers = new ArrayList<>();

        String[] temperatureSensorDirectories = getTemperatureSensorDirectories("/mnt/1wire/");
        for (String path : temperatureSensorDirectories) {
            int startIndex = path.toLowerCase().indexOf("28.ff") + "28.ff".length();
            int endIndex = path.indexOf('/', startIndex);
            String serial = path.substring(startIndex, endIndex);
            serialNumbers.add(serial);
        }

        return serialNumbers;
    }

    private String[] getTemperatureSensorDirectories(String path) {
        return new File(path).list(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                if (!new File(directory, name).isDirectory()) return false;
                if (!name.substring(0, 5).toLowerCase().equals("28.ff")) return false;
                if (!new File(directory + "/" + name + "/temperature").exists()) return false;
                return true;
            }
        });
    }
}
