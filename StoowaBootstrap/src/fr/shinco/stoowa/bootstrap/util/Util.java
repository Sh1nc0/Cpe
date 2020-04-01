package fr.shinco.stoowa.bootstrap.util;

import java.io.*;
import java.util.ArrayList;

public class Util {
    public Util() {
    }

    public static ArrayList<File> list(File folder) {
        ArrayList<File> files = new ArrayList();
        if (!folder.isDirectory()) {
            return files;
        } else {
            File[] folderFiles = folder.listFiles();
            if (folderFiles != null) {
                File[] arr$ = folderFiles;
                int len$ = folderFiles.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    File f = arr$[i$];
                    if (f.isDirectory()) {
                        files.addAll(list(f));
                    } else {
                        files.add(f);
                    }
                }
            }

            return files;
        }
    }

    public static void printAndWriteProcessOutput(Process process, File logsFile) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        final BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        final FileWriter fw = new FileWriter(logsFile);
        (new Thread() {
            public void run() {
                try {
                    String line = "";

                    try {
                        while((line = reader.readLine()) != null) {
                            fw.write(line);
                            System.out.println(line);
                        }
                    } finally {
                        fw.close();
                        reader.close();
                    }
                } catch (IOException var6) {
                }

            }
        }).start();
        (new Thread() {
            public void run() {
                try {
                    String line = "";

                    try {
                        while((line = errorReader.readLine()) != null) {
                            fw.write(line);
                            System.err.println(line);
                        }
                    } finally {
                        errorReader.close();
                    }
                } catch (IOException var6) {
                }

            }
        }).start();
    }
}