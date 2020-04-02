package fr.shinco.stoowa;


import fr.shinco.stoowa.ui.Application;
import fr.shinco.util.Dir;

import java.awt.*;
import java.io.File;

public class Main {
    public static final File DIR = Dir.createDir("Stoowa");
    public static void main(String[] args){

        EventQueue.invokeLater(() -> {
            Application application = new Application();
            application.setVisible(true);
        });
    }
}
