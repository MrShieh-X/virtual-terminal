package com.mrshiehx.virtual_terminal.system.classes.make_directory;

import com.mrshiehx.virtual_terminal.system.classes.Command;
import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;

public class MakeDirectory implements Command {
    @Override
    public void main(String[] args) {
        if (args.length < 2) usage(args[0]);
        else {
            String arg1 = args[1];
            File file = CommandFileUtils.getFile(arg1);
            if (file.exists()) {
                System.out.printf("The file \"%s\" exists.\n", arg1);
            } else {
                try {
                    file.mkdir();
                    if (!file.exists()) System.out.printf("Failed to create the directory \"%s\"\n", arg1);
                } catch (Exception e) {
                    System.out.printf("Failed to create the directory \"%s\", the error is: %s\n", arg1, e.toString());
                }
            }
        }
    }

    @Override
    public void usage(String command) {
        System.out.println("Usage: " + command + " <directory name>: Creates a directory.");
    }
}
