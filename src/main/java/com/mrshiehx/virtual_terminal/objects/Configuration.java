package com.mrshiehx.virtual_terminal.objects;

import com.mrshiehx.virtual_terminal.utils.IoStreamUtils;
import com.mrshiehx.virtual_terminal.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Configuration {
    private final Map<String, Object> map;

    public Configuration(File configurationFile) throws IOException {
        this(new FileInputStream(configurationFile));
    }

    public Configuration(FileInputStream configurationFis) throws IOException {
        this(IoStreamUtils.getString(configurationFis));
    }

    public Configuration(String configuration) {
        this();
        String[] lines;
        if (StringUtils.isNotEmpty(configuration)) {
            if (configuration.contains("\n")) {
                lines = configuration.split("\n");
            } else {
                lines = new String[]{configuration};
            }
            for (String string : lines) {
                if (StringUtils.isNotEmpty(string)) {
                    try {
                        int indexOf = string.indexOf("=");
                        String key = string.substring(0, indexOf);
                        String value = string.substring(indexOf + 1);
                        map.put(key, value);
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    public Configuration(Map<String, Object> map) {
        if (map == null) throw new NullPointerException();
        this.map = map;
    }

    public Configuration() {
        this.map = new LinkedHashMap<>();
    }

    public Object get(String key) {
        return map.get(key);
    }

    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    public String toString() {
        List<String> keys = new ArrayList<>(map.keySet());
        List<Object> values = new ArrayList<>(map.values());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < map.size(); i++) {
            stringBuilder.append(keys.get(i));
            stringBuilder.append("=");
            stringBuilder.append(values.get(i));
            stringBuilder.append("\n");
        }
        System.gc();
        return stringBuilder.toString();
    }
}
