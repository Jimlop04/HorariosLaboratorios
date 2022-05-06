/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.calendar;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ExtenderService {

    public Map<String, ExtenderExample> createExtenderExamples() {
        Properties properties = new Properties();

        try (InputStream inStream = ExtenderService.class.getResourceAsStream("/schedule-extender-examples.properties")) {
            properties.load(inStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, ExtenderExample> extenderExamples = new HashMap<>();

        for (String key : properties.stringPropertyNames()) {
            if (key != null && key.endsWith(".name")) {
                String baseKey = key.substring(0, key.length() - 5);
                ExtenderExample example = new ExtenderExample(baseKey, properties);
                if (example.getName() != null && example.getValue() != null && !example.getName().trim().isEmpty()
                        && !example.getValue().trim().isEmpty()) {
                    extenderExamples.put(baseKey, example);
                }
            }
        }

        return extenderExamples;
    }

    public static class ExtenderExample {
        private String details;
        private String html;
        private String key;
        private String link;
        private String name;
        private String value;

        public ExtenderExample(String key, Properties properties) {
            this.key = key;
            this.details = properties.getProperty(key + ".details");
            this.html = properties.getProperty(key + ".html");
            this.link = properties.getProperty(key + ".link");
            this.name = properties.getProperty(key + ".name");
            this.value = properties.getProperty(key + ".value");
        }

        public String getDetails() {
            return details;
        }

        public String getHtml() {
            return html;
        }

        public String getKey() {
            return key;
        }

        public String getLink() {
            return link;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}