package h2o.tz.lyapanov.tz_lyapanov_aa.exceptions;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(String message) {
        super(message);
    }

    public DeviceNotFoundException(String deviceType, String id) {
        super("Device " + deviceType + " not found with id " + id);
    }
}
