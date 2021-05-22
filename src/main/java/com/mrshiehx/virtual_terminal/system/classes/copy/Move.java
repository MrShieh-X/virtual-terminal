package com.mrshiehx.virtual_terminal.system.classes.copy;

import com.mrshiehx.virtual_terminal.system.classes.Command;
import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;

public class Move extends AbstractCopy implements Command {
    @Override
    public void main(String[] args) {
        super.main(args);
        if (args.length >= 3) {
            File file = CommandFileUtils.getFile(args[1]);
            File parent = CommandFileUtils.getFile(args[2]);
            File son = new File(parent, file.getName());
            if (file.isFile()) {
                if (file.exists() && son.exists() && son.length() > 0) {
                    file.delete();
                }
            } else {
                if (file.exists() && son.exists()) {
                    deleteDirectory(file);
                }
            }
        }
    }

    public void deleteDirectory(File directory) {
        if (directory != null) {
            if (directory.exists()) {
                if (directory.isDirectory()) {
                    if (directory.listFiles() != null) {
                        if (directory.listFiles().length != 0) {
                            File[] files = directory.listFiles();
                            if (files != null && files.length > 0)
                                for (File file : files) {
                                    if (file.isFile()) {
                                        String path;
                                        try {
                                            path = file.getCanonicalPath();
                                        } catch (Throwable e) {
                                            path = file.getAbsolutePath();
                                        }
                                        if (!faileds.contains(path)) {
                                            file.delete();
                                        }
                                    } else {
                                        deleteDirectory(file);
                                    }
                                }
                        }
                    }
                }
                directory.delete();
            }
        }
    }

    @Override
    String[] getStrings() {
        return new String[]{
                "Failed to move the file \"%s\"\n",
                "Failed to move the file \"%s\", the error is: %s\n",
                "There is a file failed to move. It's %s\n",
                "There are %d files failed to move. They are: \n",
                "Failed to move the directory \"%s\", the error is: %s\n"};
    }
}
