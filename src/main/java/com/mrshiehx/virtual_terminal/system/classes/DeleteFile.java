package com.mrshiehx.virtual_terminal.system.classes;

import com.mrshiehx.virtual_terminal.utils.FileUtils;
import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;

public class DeleteFile implements Command {
    @Override
    public void main(String[] args) {
        if (args.length < 2) usage(args[0]);
        else {
            String arg1 = args[1];
            File file = CommandFileUtils.getFile(arg1);
            if (!file.exists()) {
                System.out.printf("The file or directory \"%s\" does not exist.\n", arg1);
            } else {
                if (file.isFile()) {
                    try {
                        file.delete();
                        if (file.exists()) System.out.printf("Failed to delete the file \"%s\"\n", arg1);
                    } catch (Exception e) {
                        System.out.printf("Failed to delete the file \"%s\", the error is: %s\n", arg1, e.toString());
                    }
                } else {
                    try {
                        FileUtils.deleteDirectory(file);
                        file.delete();
                        if (file.exists()) System.out.printf("Failed to delete the directory \"%s\"\n", arg1);
                    } catch (Exception e) {
                        System.out.printf("Failed to delete the directory \"%s\", the error is: %s\n", arg1, e.toString());
                    }
                }
            }
        }
    }

    @Override
    public void usage(String command) {
        System.out.println("Usage: " + command + " <a file or a directory>: Deletes a file or a directory with its sub files.");
    }
}
