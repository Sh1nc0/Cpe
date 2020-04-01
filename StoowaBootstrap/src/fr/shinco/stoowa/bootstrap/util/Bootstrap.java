package fr.shinco.stoowa.bootstrap.util;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Bootstrap {
    private LauncherClasspath launcherClasspath;
    private LauncherInfos launcherInfos;
    private LaunchInfos launchInfos;

    public Bootstrap(LauncherClasspath launcherClasspath, LauncherInfos launcherInfos) {
        this.launcherClasspath = launcherClasspath;
        this.launcherInfos = launcherInfos;
    }

    public Bootstrap(LauncherClasspath launcherClasspath,LauncherInfos launcherInfos, LaunchInfos launchInfos) {
        this.launcherClasspath = launcherClasspath;
        this.launcherInfos = launcherInfos;
        this.launchInfos = launchInfos;
    }

    public Process launch() throws IOException {
        this.printInfos();
        ProcessBuilder pb = new ProcessBuilder(new String[0]);
        ArrayList<String> commands = new ArrayList();
        commands.add(this.getJavaPath());
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            commands.addAll(Arrays.asList(this.getMacArgs()));
        }

        if (this.launchInfos != null) {
            commands.addAll(Arrays.asList(this.launchInfos.getVmArgs()));
        }

        commands.add("-cp");
        commands.add(this.constructClasspath());
        commands.add(this.launcherInfos.getMainClass());
        if (this.launchInfos != null) {
            commands.addAll(Arrays.asList(this.launchInfos.getArgs()));
        }

        String entireCommand = "";

        String cmd;
        for(Iterator i$ = commands.iterator(); i$.hasNext(); entireCommand = entireCommand + cmd + "\n") {
            cmd = (String)i$.next();
        }

        System.out.println("[Stoowa] Entire command : ");
        System.out.println(entireCommand);
        System.out.println("[Stoowa] Launching launcher");
        pb.directory(this.launcherClasspath.getLauncher().getParentFile());
        pb.command(commands);
        Process p = pb.start();
        System.out.println("[Stoowa] Successfully launched");
        File logsFile = new File(this.launcherClasspath.getLauncher().getParentFile(), "bootstraplogs.txt");
        Util.printAndWriteProcessOutput(p, logsFile);
        return p;
    }

    private void printInfos() {
        System.out.println("[Stoowa] Stoowa v1.0 Bootstrap");
        System.out.println("[Stoowa] Generating command with : ");
        System.out.println("[Stoowa]    Launcher Classpath :");
        System.out.println("[Stoowa]        Launcher     : " + this.launcherClasspath.getLauncher().getAbsolutePath());
        System.out.println("[Stoowa]        Libs Folder  : " + this.launcherClasspath.getLibsFolder().getAbsolutePath());
        System.out.println("[Stoowa]    Launcher Infos :");
        System.out.println("[Stoowa]        Name  : " + this.launcherInfos.getServerName());
        System.out.println("[Stoowa]        Main Class   : " + this.launcherInfos.getMainClass());
        if (this.launchInfos != null) {
            System.out.println("[Stoowa]    Launch Infos :");
            System.out.print("[Stoowa]        Arguments    : ");
            String[] arr$ = this.launchInfos.getArgs();
            int len$ = arr$.length;

            int i$;
            String arg;
            for(i$ = 0; i$ < len$; ++i$) {
                arg = arr$[i$];
                System.out.print(arg);
            }

            System.out.print("\n[OpenLauncherLib]        VM Arguments : ");
            arr$ = this.launchInfos.getVmArgs();
            len$ = arr$.length;

            for(i$ = 0; i$ < len$; ++i$) {
                arg = arr$[i$];
                System.out.print(arg);
            }
        }

        System.out.println("[Stoowa] Generating launch command...");
    }

    private String getJavaPath() {
        return System.getProperty("os.name").toLowerCase().contains("win") ? "\"" + System.getProperty("java.home") + "/bin/java" + "\"" : System.getProperty("java.home") + "/bin/java";
    }

    private String[] getMacArgs() {
        String[] macArgs = new String[]{"-Xdock:name=" + this.launcherInfos.getServerName(), "-XX:+UseConcMarkSweepGC", "-XX:+CMSIncrementalMode", "-XX:-UseAdaptiveSizePolicy"};
        return macArgs;
    }

    public String constructClasspath() {
        String classpath = "";
        ArrayList<File> libs = Util.list(this.launcherClasspath.getLibsFolder());
        String separator = System.getProperty("path.separator");

        File lib;
        for(Iterator i$ = libs.iterator(); i$.hasNext(); classpath = classpath + lib.getAbsolutePath() + separator) {
            lib = (File)i$.next();
        }

        classpath = classpath + this.launcherClasspath.getLauncher().getAbsolutePath();
        return classpath;
    }

    public LauncherClasspath getLauncherClasspath() {
        return this.launcherClasspath;
    }

    public LauncherInfos launcherInfos() {
        return this.launcherInfos;
    }

    public LaunchInfos getLaunchInfos() {
        return this.launchInfos;
    }
}