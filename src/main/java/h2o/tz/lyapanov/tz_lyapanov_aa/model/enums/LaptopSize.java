package h2o.tz.lyapanov.tz_lyapanov_aa.model.enums;

public enum LaptopSize {
    INCH_13("13 дюймов"),
    INCH_14("14 дюймов"),
    INCH_15("15 дюймов"),
    INCH_17("17 дюймов");

    private String value;

    LaptopSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
