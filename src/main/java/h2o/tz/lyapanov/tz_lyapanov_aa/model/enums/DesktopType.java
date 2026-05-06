package h2o.tz.lyapanov.tz_lyapanov_aa.model.enums;

public enum DesktopType {
    DESKTOP_TYPE("Десктоп"),
    MONOBLOCK_TYPE("Моноблок"),
    NETTOP_TYPE("Неттоп");

    private String type;

    DesktopType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
