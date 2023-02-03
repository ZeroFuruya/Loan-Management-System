package e2p.icotp.service;

import java.io.File;
import java.util.prefs.Preferences;

import e2p.icotp.App;

public class RegistryService {
    private static final String REGISTRY_KEY = "XMLDataFilePathAdminManager";

    public static void setXML_toRegistry(App app, File xml){
        Preferences prefs = Preferences.userNodeForPackage(App.class);
        if(xml != null){
            prefs.put(REGISTRY_KEY, xml.getPath());
            app.getMainStage().setTitle("Admin - " + xml.getName());
        }
        else{
            prefs.remove("key");
            app.getMainStage().setTitle("Admin");
        }
    }

    public static File getXML_FromRegistry(){
        Preferences prefs  = Preferences.userNodeForPackage(App.class);
        String xmlPath = prefs.get(REGISTRY_KEY, null);

        return xmlPath != null ? new File(xmlPath) : null;
    }
}
