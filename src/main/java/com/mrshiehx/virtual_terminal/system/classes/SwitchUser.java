package com.mrshiehx.virtual_terminal.system.classes;

import com.mrshiehx.virtual_terminal.VirtualTerminal;
import com.mrshiehx.virtual_terminal.data.DataFiles;
import com.mrshiehx.virtual_terminal.operators.UserOperator;

import java.io.File;
import java.io.IOException;

public class SwitchUser implements Command {
    public void main(String[] args) {
        if (args.length < 2) {
            usage(args[0]);
        } else {
            String user = args[1];
            if (user.equals("/?") || user.equals("/help")) {
                usage(args[0]);
            } else {
                File file = DataFiles.getUserFile(user);
                if (file.exists()) {
                    try {
                        VirtualTerminal.currentUser = UserOperator.read(file);
                    } catch (IOException | ClassNotFoundException e) {

                        System.out.printf("Failed to switch to the user \"%s\", the error is: %s\n", user, e.toString());
                    }
                } else {
                    System.out.printf("User \"%s\" does not exist.\n", user);
                }
            }
        }
    }

    public void usage(String command) {
        System.out.println("Usage: " + command + " <target user>: Switches the current user to a new user.");
    }
}
