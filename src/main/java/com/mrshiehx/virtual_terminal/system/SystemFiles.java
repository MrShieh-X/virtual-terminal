package com.mrshiehx.virtual_terminal.system;

import com.mrshiehx.virtual_terminal.constants.Extensions;

import java.io.File;

import static com.mrshiehx.virtual_terminal.system.SystemDirectories.*;

public class SystemFiles {
    private SystemFiles() {
    }

    public static File DEVICE_INFORMATION = new File(CONFIGURATIONS, "device_information" + Extensions.KEYS_AND_VALUES);

    public static void init() {
        DEVICE_INFORMATION = new File(CONFIGURATIONS, "device_information" + Extensions.KEYS_AND_VALUES);
    }
}
