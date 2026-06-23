package utilClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {
    
    public static String getPropertiesValueByKey(String fileName, String Key){
        Properties properties=new Properties();
        FileInputStream fis=null;
             try {
                fis=new FileInputStream("src/test/resources/test data/"+fileName+".properties");
            } catch (FileNotFoundException e) {
                System.out.println("File not found for properties");
            }
        try {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(Key);
    }

    public static void setProperties(String fileName, String key, String value){
        Properties properties=new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/test data/"+fileName+".properties")) {
            properties.load(fis);
            properties.setProperty(key, value);
        } catch (Exception e) {
            System.out.println("Could not load existing properties, creating new file");
        }
        try (FileOutputStream fos= new FileOutputStream("src/test/resources/test data/"+fileName+".properties")) {
            properties.store(fos, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
