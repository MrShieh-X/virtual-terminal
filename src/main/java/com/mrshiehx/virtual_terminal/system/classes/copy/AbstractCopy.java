package com.mrshiehx.virtual_terminal.system.classes.copy;

import com.mrshiehx.virtual_terminal.system.classes.Command;
import com.mrshiehx.virtual_terminal.utils.FileUtils;
import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractCopy implements Command {
    protected java.util.List<String> faileds = new ArrayList<>();

    @Override
    public void main(String[] args) {
        if (args.length < 3) usage(args[0]);
        else {
            String arg1 = args[1];
            String arg2 = args[2];
            File file = CommandFileUtils.getFile(arg1);
            if (!file.exists()) {
                System.out.printf("The file or directory \"%s\" does not exist.\n", arg1);
            } else {
                File parent = CommandFileUtils.getFile(arg2);
                File son = new File(parent, file.getName());
                if (son.exists()) {
                    if (file.isFile()) {
                        System.out.printf("The destination file \"%s\" is existed.\n", arg2);
                    } else {
                        /*String path;
                        try{path=son.getCanonicalPath();}catch (Exception e){path=son.getAbsolutePath();}
                        switch (FileUtils.ros(path)){
                            case 0:
                                break;
                            default:
                                return;
                        }*/

                        realCopyDirectory(file, parent, arg1);
                    }
                } else {
                    if (file.isFile()) {
                        try {
                            son.createNewFile();
                            if (!son.exists()) {
                                System.out.printf(getStrings()[0], arg1);
                                return;
                            }
                            FileUtils.copy(file, son);
                        } catch (IOException e) {
                            System.out.printf(getStrings()[1], arg1, e.toString());
                        }
                    } else {
                        realCopyDirectory(file, parent, arg1);
                    }
                }
            }
        }
    }

    void realCopyDirectory(File file, File parent, String arg1) {
        try {
                            /*if (!son.exists()){
                                System.out.printf("Failed to copy the directory \"%s\"\n", arg1);
                                return;
                            }*/
            FileUtils.copyDirectory(file, parent.getAbsolutePath(), file.getName(), faileds);
            FileUtils.allreplace = false;
            FileUtils.allskip = false;
            if (faileds.size() == 1) {
                System.out.printf(getStrings()[2], faileds.get(0));
            } else if (faileds.size() > 1) {
                System.out.printf(getStrings()[3], faileds.size());
                for (String s : faileds) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            System.out.printf(getStrings()[4], arg1, e.toString());
        }
    }

    abstract String[] getStrings();

    @Override
    public void usage(String command) {
        System.out.println("Usage: " + command + " <file or directory> <the destination directory>: Copies a file or a directory. The second argument is the parent directory of the destination file.");
    }
}
