package fr.shinco.stoowa.bootstrap.util;

import java.io.File;

public class LauncherClasspath {
    private File launcher;
    private File libsFolder;

    public LauncherClasspath(File launcher) {
        this(launcher, (File)null);
    }

    public LauncherClasspath(File launcher, File libsFolder) {
        this.launcher = launcher;
        this.libsFolder = libsFolder;
    }

    public File getLauncher() {
        return this.launcher;
    }

    public File getLibsFolder() {
        return this.libsFolder;
    }
}
