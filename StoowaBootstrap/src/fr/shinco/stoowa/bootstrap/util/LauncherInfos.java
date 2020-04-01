package fr.shinco.stoowa.bootstrap.util;

public class LauncherInfos {
    private String name;
    private String mainClass;

    public LauncherInfos(String name, String mainClass) {
        this.name = name;
        this.mainClass = mainClass;
    }

    public String getServerName() {
        return this.name;
    }

    public String getMainClass() {
        return this.mainClass;
    }
}
