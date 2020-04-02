package fr.shinco.stoowa.util.loader;

import fr.shinco.stoowa.Main;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class GameLoader {
    public static List<URL> urls = new ArrayList<URL>();

    public static List<String> getGameClasses(){
        List<String> classes = new ArrayList<String>();

        File[] files = new File(Main.DIR, "games").listFiles(new GameFilter());

        for (File f : files){
            JarFile  jarFile = null;

            try{
                jarFile = new JarFile(f);

                Manifest manifest = jarFile.getManifest();

                String gameClassName = manifest.getMainAttributes().getValue("Game-Class");

                classes.add(gameClassName);

                urls.add(f.toURI().toURL());

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(jarFile != null){
                    try{
                        jarFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return classes;
    }

    private static class GameFilter implements FileFilter{
        @Override
        public boolean accept(File file) {
            return file.isFile() && file.getName().toLowerCase().endsWith(".jar");
        }
    }

}
