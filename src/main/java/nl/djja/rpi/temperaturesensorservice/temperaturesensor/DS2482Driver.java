package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

//import org.glassfish.hk2.utilities.reflection.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DS2482Driver implements Runnable{ // TODO Should probably make this a singleton since we use the static methods and variables
    private static HashMap<String, Float> readings = new HashMap<>();
    private static boolean cancelled;

    public static void addSerialNumber(String serialNumber){
        readings.put(serialNumber, -3f);
    }

    public static float getTemperature(String serial){
        return readings.get(serial);
    }

    private static void registerConnectedTemperatureSensors() {

    }

    private static String[] getTemperatureSensorDirectories(String path) {
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

    private static float readTemperatureFromDevice(String serialNumber) {
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

    public static void cancel(){
        cancelled = true;
    }

    public void run() { // TODO Can I run this multiple times?
        cancelled = false;
        while(!cancelled){
            for(Map.Entry<String, Float> map : readings.entrySet()){
                map.setValue(readTemperatureFromDevice(map.getKey()));
            }

            try {
                Thread.sleep(1000);         // TODO this sleep is longer than the routine sleep
            } catch (InterruptedException e) {
//                Logger.printThrowable(e);
            }
        }
    }
}
