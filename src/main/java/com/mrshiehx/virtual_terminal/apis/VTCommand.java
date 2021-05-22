package com.mrshiehx.virtual_terminal.apis;

public abstract class VTCommand {
    protected final java.io.File currentDirectory;
    protected final Object currentUser;
    protected final boolean echoOpen;

    public abstract void main(String[] args);

    public abstract void usage(String command);

    public VTCommand(java.io.File currentDirectory, Object currentUser, boolean echoOpen) {
        this.currentDirectory = currentDirectory;
        this.currentUser = currentUser;
        this.echoOpen = echoOpen;
    }

    public java.io.File getCurrentDirectory() {
        return currentDirectory;
    }

    public Object getCurrentUser() {
        return currentUser;
    }

    public boolean isEchoOpen() {
        return echoOpen;
    }

    public static String getUserName(Object user) {
        try {
            return (String) user.getClass().getField("userName").get(String.class);
        } catch (Throwable e) {
            return "";
        }
    }

    public static String getUserDisplayName(Object user) {
        try {
            return (String) user.getClass().getField("userDisplayName").get(String.class);
        } catch (Throwable e) {
            return "";
        }
    }

    public static String getUserPermissions(Object user) {
        try {
            return (String) user.getClass().getField("userPermissions").get(String.class);
        } catch (Throwable e) {
            return "";
        }
    }
}
