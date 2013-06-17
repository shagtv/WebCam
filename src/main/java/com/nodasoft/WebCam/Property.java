package com.nodasoft.WebCam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class Property {

    static HashMap<String, String> properties = new HashMap<String, String>();
    static XMLConfiguration config;

    static void init() {
        try {
            config = new XMLConfiguration("conf.xml");
        } catch (ConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        for (Iterator iter = config.getKeys(); iter.hasNext();) {
            String item = (String) iter.next();
            properties.put(item, (String)config.getProperty(item));
        }
    }

    static void save() {
        config.setEncoding("UTF-8");
        config.setRootElementName("sample-xml-configuration");
        for (Entry<String, String> h : properties.entrySet()) {
            config.setProperty(h.getKey(), h.getValue());
        }
        try {
            config.save();
        } catch (ConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static String get(String key){
        return (String)properties.get(key);
    }
    
    static void set(String key, String value){
        properties.put(key, value);
    }
}
