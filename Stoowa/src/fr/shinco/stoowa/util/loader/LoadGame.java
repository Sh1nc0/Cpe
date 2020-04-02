package fr.shinco.stoowa.util.loader;

import com.shinco.stoowakit.StoowaKit;

import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class LoadGame {

    private static  ClassLoader classLoader;

    public static List<StoowaKit> LoadGame(){
        List<StoowaKit> stoowaKits = new ArrayList<StoowaKit>();
        List<String> classes = GameLoader.getGameClasses();
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                classLoader = new URLClassLoader(
                        GameLoader.urls.toArray(new URL[GameLoader.urls.size()]),
                        GameLoader.class.getClassLoader()
                );
                return null;
            }
        });
        for(String c : classes){
            try{
                Class<?> gameClass = Class.forName(c, true, classLoader);
                if(StoowaKit.class.isAssignableFrom(gameClass)){
                    Class<StoowaKit> castedClass = (Class<StoowaKit>)gameClass;
                    StoowaKit stoowaKit = castedClass.newInstance();
                    stoowaKits.add(stoowaKit);
                }
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
        return  stoowaKits;
    }
}
