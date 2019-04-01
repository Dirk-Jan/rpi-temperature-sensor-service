package nl.djja.rpi.temperaturesensorservice.temperaturesensor;

public class DS2482TemperatureSensor implements TemperatureSensor{
    private String serialNumber;

    public DS2482TemperatureSensor(String serialNumber) {
        this.serialNumber = serialNumber;
        DS2482Driver.addSerialNumber(serialNumber);
    }

    public float readTemperature() {
        return DS2482Driver.getTemperature(serialNumber);
    }

    @Override
    public String getSerial() {
        return serialNumber;
    }
}
