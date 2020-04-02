package fr.shinco.stoowa.ui;

import fr.shinco.stoowa.Const;
import fr.shinco.stoowa.util.loader.Core;

import javax.swing.*;

public class Application extends JFrame {

    public Application(){
        initUI();
    }

    private void initUI(){
        Core.launch();
        setSize(Const.WIDTH, Const.HEIGHT);
        setTitle("Stoowa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
