package com.mrshiehx.virtual_terminal.system.classes;

import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;
import java.io.IOException;

public class CreateFile implements Command {
    public void main(String[] args) {
        if (args.length < 2) usage(args[0]);
        else {
            String arg1 = args[1];
            File file = CommandFileUtils.getFile(arg1);
            if (file.exists()) {
                System.out.printf("The file \"%s\" exists.\n", arg1);
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.printf("Failed to create the file \"%s\", the error is: %s\n", arg1, e.toString());
                }
            }
        }
    }

    public void usage(String command) {
        System.out.println("Usage: " + command + " <file name>: Creates a new file.");
    }
}
