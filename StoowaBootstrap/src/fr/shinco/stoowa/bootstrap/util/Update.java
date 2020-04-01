package fr.shinco.stoowa.bootstrap.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Update {
    private String url;
    private File path;

    public Update(String url, File path){
        this.url = url;
        this.path = path;
    }

    public void start(){
        System.out.println("[Stoowa] Download Files...");
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOS = new FileOutputStream(path)) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
        }
    }
}
