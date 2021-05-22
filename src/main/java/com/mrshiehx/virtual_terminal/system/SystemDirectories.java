package com.mrshiehx.virtual_terminal.system;

import java.io.File;

import static com.mrshiehx.virtual_terminal.system.RootDirectories.*;

public class SystemDirectories {
    private SystemDirectories() {
    }

    public static File SYSTEM = new File(com.mrshiehx.virtual_terminal.VirtualTerminal.firstDirectory, "system");
    public static File CLASSES = new File(SYSTEM, "classes");
    public static File CONFIGURATIONS = new File(SYSTEM, "configurations");

    public static void init() {
        SYSTEM = new File(com.mrshiehx.virtual_terminal.VirtualTerminal.firstDirectory, "system");
        CLASSES = new File(SYSTEM, "classes");
        CONFIGURATIONS = new File(SYSTEM, "configurations");
    }
}
