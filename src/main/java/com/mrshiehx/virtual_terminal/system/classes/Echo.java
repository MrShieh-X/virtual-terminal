package com.mrshiehx.virtual_terminal.system.classes;

import com.mrshiehx.virtual_terminal.VirtualTerminal;
import com.mrshiehx.virtual_terminal.utils.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils.*;

public class Echo implements Command {
    public void main(String[] args) {
        if (args.length < 2) {
            System.out.println();
        } else {
            if (args[1].startsWith("/? ") || args[1].startsWith("/help ")) {
                usage(args[0]);
            } else if (args[1].equals("on")) {
                VirtualTerminal.echoOpen = true;
            } else if (args[1].equals("off")) {
                VirtualTerminal.echoOpen = false;
            }/* else if (args[1].equals("-f") || args[1].equals("-file") || args[1].equals("--f") || args[1].equals("--file")) {
                usage(args[0]);
            }  */ else if (args[1].equals("-f") || args[1].equals("-file") || args[1].equals("--f") || args[1].equals("--file")) {
                echoFile(args);
            } else {
                System.out.println(args[1]);
            }
        }
    }

    private void echoFile(String[] args) {
        if (args.length < 3) {
            usage(args[0]);
        } else {
            File file = getFile(args[2]);
            if (!file.exists()) {
                printsFileNotExist(args[2]);
            } else {
                if (file.isDirectory()) {
                    printsNotFile(args[2]);
                } else {
                    if (args.length > 3) {
                        int length = -1;
                        try {
                            length = Integer.parseInt(args[3]);
                        } catch (Throwable ignored) {
                        }
                        if (length < 0) printsFileByAll(file, args[2]);
                        else printsFileByLength(file, length, args[2]);
                    } else printsFileByAll(file, args[2]);
                }
            }
        }
    }

    private void printsFileByAll(File file, String args2) {
        try {
            System.out.println(FileUtils.getString(file));
            //System.out.println();
        } catch (IOException e) {
            printsError(args2, e);
        }
    }

    private void printsFileByLength(File file, int length, String args2) {
        try {
            FileReader fr = new FileReader(file);
            char[] a = new char[length];
            fr.read(a); // 读取数组中的内容
            for (char c : a)
                if (c != 0) System.out.print(c); // 一个一个打印字符
            System.out.println();
            fr.close();
        } catch (IOException e) {
            printsError(args2, e);
        }
    }

    private void printsError(String file, Object error) {
        System.out.printf("Failed to print the file \"%s\", the error is: %s\n", file, error);
    }

    public void usage(String command) {
        System.out.println("Usage: " + command + " <message>: Prints a message.");
        System.out.println("       " + command + " <on|off>: Enables or disables command echo.");
        System.out.println("       " + command + " -f <file name> <(optional)length of the content>: Prints the content of the file. The default length is all.");
    }
}
