package nl.djja.rpi.temperaturesensorservice.rest.util;

import nl.djja.rpi.temperaturesensorservice.rest.util.exceptions.StringIsNotANumberException;

public class Util { // TODO Never used
    public static Long stringToLong(String value) throws StringIsNotANumberException {
        Long result;
        try {
            result = Long.parseLong(value);
        } catch (Exception ex) {
            throw new StringIsNotANumberException();
        }
        return result;
    }

    public static int stringToInteger(String value) throws StringIsNotANumberException {
        int result;
        try {
            result = Integer.parseInt(value);
        } catch (Exception ex) {
            throw new StringIsNotANumberException();
        }
        return result;
    }
}
