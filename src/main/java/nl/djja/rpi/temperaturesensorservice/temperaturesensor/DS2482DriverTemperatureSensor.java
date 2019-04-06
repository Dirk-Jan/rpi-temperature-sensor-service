package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

public class DS2482DriverTemperatureSensor implements TemperatureSensor{
    private String serialNumber;

    public DS2482DriverTemperatureSensor(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public float readTemperature() {
        return DS2482Driver.getTemperature(serialNumber);
    }

    @Override
    public String getSerial() {
        return serialNumber;
    }
}
