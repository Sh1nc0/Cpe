package fr.shinco.stoowa.bootstrap;

import fr.shinco.Shinco;
import fr.shinco.stoowa.bootstrap.util.Bootstrap;
import fr.shinco.stoowa.bootstrap.util.LauncherClasspath;
import fr.shinco.stoowa.bootstrap.util.LauncherInfos;
import fr.shinco.stoowa.bootstrap.util.Update;
import fr.shinco.util.Dir;
import fr.shinco.util.SplashScreen;
import fr.theshark34.openlauncherlib.util.GameDir;

import java.io.File;
import java.io.IOException;

public class StoowaBootstrap {
    private static SplashScreen splash;

    private static final LauncherInfos INFOS = new LauncherInfos("Stoowa", "fr.shinco.stoowa.Main");
    private static final File DIR = Dir.createDir("Stoowa");
    private static final LauncherClasspath CP = new LauncherClasspath(new File(DIR, "Launcher/Stoowa.jar"), new File(DIR, "Launcher/Libs"));

    public static void main(String[] args) throws IOException {
        Shinco.setResourcePath("/fr/shinco/stoowa/bootstrap/resources/");
        displaySplash();
        doUpdate();
        launch();
    }

    private static void displaySplash(){
        splash = new SplashScreen("Stoowa 0.0.1",  Shinco.getResource("splash.png"));
        splash.setBackground(Shinco.TRANSPARENT);
        splash.getContentPane().setBackground(Shinco.TRANSPARENT);
        splash.setVisible(true);
    }

    private static void doUpdate(){
        new File(DIR, "Launcher").mkdir();

        Update update = new Update("http://giorty.fr/stoowa/launcher/Stoowa.jar",new File(DIR, "Launcher/Stoowa.jar"));
        update.start();
    }

    private static void launch() throws IOException {
        Bootstrap bootstrap = new Bootstrap(CP, INFOS);
        Process p = bootstrap.launch();

        splash.setVisible(false);
        try {
            p.waitFor();
        } catch (InterruptedException e){

        }
        System.exit(0);

    }
}
