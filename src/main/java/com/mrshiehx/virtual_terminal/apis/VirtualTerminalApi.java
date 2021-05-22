package com.mrshiehx.virtual_terminal.apis;

public class VirtualTerminalApi {
    /**
     * Gets the current directory.
     *
     * @return the current directory
     */
    public static java.io.File getCurrentDirectory() {
        try {
            return (java.io.File) Class.forName("com.mrshiehx.virtual_terminal.VirtualTerminal").getField("currentDirectory").get(java.io.File.class);
        } catch (Exception e) {

        }
        return new java.io.File(".");
    }

    /**
     * Gets the current user.
     *
     * @return the current user, you can cast it to {@link com.mrshiehx.virtual_terminal.objects.User}
     */
    public static com.mrshiehx.virtual_terminal.objects.User getCurrentUser() {
        try {
            return (com.mrshiehx.virtual_terminal.objects.User) Class.forName("com.mrshiehx.virtual_terminal.VirtualTerminal").getField("currentUser").get(com.mrshiehx.virtual_terminal.objects.User.class);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Gets the status of command echo.
     *
     * @return status, enabled(on) or disabled(off)
     */
    public static boolean isEchoOpen() {
        try {
            return Class.forName("com.mrshiehx.virtual_terminal.VirtualTerminal").getField("echoOpen").getBoolean(boolean.class);
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * Gets the default user.
     *
     * @return the default user, you can cast it to {@link com.mrshiehx.virtual_terminal.objects.User}
     */
    public static com.mrshiehx.virtual_terminal.objects.User getDefaultUser() {
        try {
            return (com.mrshiehx.virtual_terminal.objects.User) Class.forName("com.mrshiehx.virtual_terminal.VirtualTerminal").getField("defaultUser").get(com.mrshiehx.virtual_terminal.objects.User.class);
        } catch (Exception e) {

        }
        return null;
    }
}
