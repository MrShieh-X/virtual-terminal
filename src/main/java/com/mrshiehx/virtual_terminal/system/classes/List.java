package com.mrshiehx.virtual_terminal.system.classes;

import com.mrshiehx.virtual_terminal.VirtualTerminal;
import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;

public class List implements Command {
    public void main(String[] args) {
        if (args.length < 2) {
            try {
                ls(VirtualTerminal.currentDirectory, VirtualTerminal.currentDirectory.getCanonicalPath());
            } catch (Exception e) {
                ls(VirtualTerminal.currentDirectory, VirtualTerminal.currentDirectory.getAbsolutePath());
            }
        } else {
            String arg1 = args[1];
            if (arg1.equals("/?") || arg1.equals("/help")) usage(args[0]);
            else {
                File file = CommandFileUtils.getFile(arg1);
                /*if (arg1.replace('\\', '/').startsWith("/")) {
                    file = new File(RootDirectories.ROOT, arg1);
                } else {
                    file = new File(VirtualTerminal.currentDirectory, arg1);
                }*/
                ls(file, arg1);
            }
        }
    }

    private void ls(File file, String filei) {
        if (!file.exists()) {
            System.out.printf("%s: No such file or directory\n", filei);
        } else {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null && files.length != 0) {
                    for (File fileX : files) {
                        System.out.println(fileX.getName());
                    }
                } else {
                    System.out.println();
                }
            } else {
                System.out.printf("%s: Not a directory\n", filei);
            }
        }
    }

    public void usage(String command) {
        System.out.println("Usage: " + command + ": Lists all files in the current directory.");
        System.out.println("       " + command + " <a directory>: Lists all files in the target directory.");
    }
}
