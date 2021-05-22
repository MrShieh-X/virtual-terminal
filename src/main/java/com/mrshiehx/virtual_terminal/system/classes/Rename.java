package com.mrshiehx.virtual_terminal.system.classes;

import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;

public class Rename implements Command {
    @Override
    public void main(String[] args) {
        if (args.length < 3) usage(args[0]);
        else {
            String arg1 = args[1], arg2 = args[2];
            File file = CommandFileUtils.getFile(arg1);
            if (!file.exists()) System.out.printf("%s: No such file or directory\n", arg1);
            else {
                File file2 = CommandFileUtils.getFile(arg2);
                if (file2.exists()) System.out.printf("%s: The target file or directory is existed.\n", arg2);
                else {
                    try {
                        file.renameTo(file2);
                        if (file.exists()) System.out.printf("Failed to rename the file \"%s\"\n", arg1);
                    } catch (Exception e) {
                        System.out.printf("Failed to rename the file \"%s\", the error is: %s\n", arg1, e);
                    }
                }
            }
        }
    }

    @Override
    public void usage(String command) {
        System.out.println("Usage: " + command + " <file or directory> <new name>: Renames a file or directory");
    }
}
