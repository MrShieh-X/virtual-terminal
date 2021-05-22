package com.mrshiehx.virtual_terminal.getters;

import com.mrshiehx.virtual_terminal.objects.Configuration;
import com.mrshiehx.virtual_terminal.system.SystemFiles;
import com.mrshiehx.virtual_terminal.utils.StringUtils;

import java.io.IOException;

public class DeviceInformationGetter {
    private DeviceInformationGetter() {
    }

    public static String getDeviceName() {
        try {
            String s = (String) new Configuration(SystemFiles.DEVICE_INFORMATION).get("DEVICE_NAME");
            if (StringUtils.isEmpty(s)) return "terminal";
            return s;
        } catch (IOException e) {
            return "terminal";
        }
    }
}
