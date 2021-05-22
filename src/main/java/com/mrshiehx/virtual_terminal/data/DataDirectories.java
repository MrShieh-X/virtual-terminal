package com.mrshiehx.virtual_terminal.data;

import java.io.File;

import static com.mrshiehx.virtual_terminal.system.RootDirectories.*;

public class DataDirectories {
    private DataDirectories() {
    }

    public static File DATA = new File(ROOT, "data");
    public static File OBJECTS = new File(DATA, "objects");
    public static File TERMINAL = new File(DATA, "terminal");
    public static File OBJECTS_USERS = new File(OBJECTS, "users");
    public static File TERMINAL_LOGS = new File(TERMINAL, "logs");
    public static File TERMINAL_ERROR_LOGS = new File(TERMINAL, "error_logs");
    public static File CLASSES = new File(DATA, "classes");

    public static void init() {
        DATA = new File(ROOT, "data");
        OBJECTS = new File(DATA, "objects");
        OBJECTS_USERS = new File(OBJECTS, "users");
        TERMINAL = new File(DATA, "terminal");
        OBJECTS_USERS = new File(OBJECTS, "users");
        TERMINAL_LOGS = new File(TERMINAL, "logs");
        TERMINAL_ERROR_LOGS = new File(TERMINAL, "error_logs");
        CLASSES = new File(DATA, "classes");
    }
}
