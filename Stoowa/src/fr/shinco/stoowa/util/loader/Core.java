package fr.shinco.stoowa.util.loader;

import com.shinco.stoowakit.StoowaKit;

import java.util.List;
import java.util.logging.LogManager;

public class Core {
    private static List<StoowaKit> games = LoadGame.LoadGame();

    public static void launch(){
        System.out.println("Chargement des Jeux");

        for (StoowaKit game : games){
            game.plug();
            System.out.println(game.getName() + " OK ");
        }
    }
    public static void exit(){
        for (StoowaKit game : games){
            game.unplug();
            System.out.println("Fermeture");
        }
    }
}
