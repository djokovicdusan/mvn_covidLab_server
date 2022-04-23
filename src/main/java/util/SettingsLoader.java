/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dule Djo
 */
public class SettingsLoader {
        private Properties properties;
    private static SettingsLoader instance;

    private SettingsLoader() {
        try {
            properties = new Properties();
            File propertyGetter = new File("settings.properties");
            boolean propertyExists = propertyGetter.exists();
            System.out.println("Postoji property fajl: "+ propertyExists);
            if(!propertyExists){
                propertyGetter.createNewFile();
                FileWriter fileWriter = new FileWriter("settings.properties");
                BufferedWriter infoWritter = new BufferedWriter(fileWriter);
                infoWritter.write("url=%n");
                infoWritter.write("username=username%n");
                infoWritter.write("password=%n");
                properties.load(new FileInputStream("settings.properties"));
            }
            else{
                properties.load(new FileInputStream("settings.properties"));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SettingsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SettingsLoader getInstance() {
        if (instance == null) {
            instance = new SettingsLoader();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "n/a");
    }
    
    public void setProperty(String key,String value) {
        properties.setProperty(key, value);
        
    }
    public void store() throws Exception{
        properties.store(new FileOutputStream("settings.properties"), "");
    }
    
}
