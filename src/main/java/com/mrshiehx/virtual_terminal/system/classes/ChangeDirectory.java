package com.mrshiehx.virtual_terminal.system.classes;

import com.mrshiehx.virtual_terminal.VirtualTerminal;
import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;

public class ChangeDirectory implements Command {
    public void main(String[] args) {
        if (args.length >= 2) {
            if (args[1].equals("/?") || args[1].equals("/help")) {
                usage(args[0]);
            } else {
                String arg1 = args[1].replace('\\', '/');
                File file = CommandFileUtils.getFile(arg1);
                if (file.exists()) {
                    if (file.isFile()) {
                        System.out.printf("%s: Not a directory\n", args[1]);
                    } else {
                        VirtualTerminal.currentDirectory = file;
                    }
                } else {
                    System.out.printf("%s: No such file or directory\n", args[1]);
                }
            }
        } else {
            try {
                System.out.println(VirtualTerminal.currentDirectory.getCanonicalPath());
            } catch (Exception e) {
                System.out.println(VirtualTerminal.currentDirectory.getAbsolutePath());
            }
        }
    }

    public void usage(String command) {
        System.out.println("Usage: " + command + ": Prints the path of the current directory.");
        System.out.println("       " + command + " <directory path>: Changes the current directory to new directory.");
    }
}
