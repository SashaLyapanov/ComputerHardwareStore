package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import java.util.List;

public interface DeviceCRUDService<REQUEST, RESPONSE> {

    RESPONSE getDevice(String id);

    List<RESPONSE> getAllDevices();

    RESPONSE createDevice(REQUEST request);

    RESPONSE updateDevice(String id, REQUEST request);

}
