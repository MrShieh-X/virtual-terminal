package com.mrshiehx.virtual_terminal.system.classes.delete_object;

import com.mrshiehx.virtual_terminal.data.DataFiles;
import com.mrshiehx.virtual_terminal.system.classes.Command;

import java.io.File;

public class DeleteUser implements Command {
    @Override
    public void main(String[] args) {
        if (args.length >= 3 && (args[2].equals("/?") || args[2].equals("/help"))) usage(args[0]);
        else {
            if (args.length >= 3) {
                File file = DataFiles.getUserFile(args[2]);
                if (file.exists()) {
                    try {
                        file.delete();
                        if (file.exists()) System.out.printf("%s: Failed to delete the target user.\n", args[2]);
                    } catch (Exception e) {
                        if (file.exists())
                            System.out.printf("%s: Failed to delete the target user, the error is: %s\n", args[2], e);
                    }
                } else {
                    System.out.printf("%s: The target user does not exist.\n", args[2]);
                }
            } else usage(args[0]);
        }
    }

    @Override
    public void usage(String command) {
        System.out.println("Usage: " + command + " -u <user name>: Deletes an existed user.");
    }
}
