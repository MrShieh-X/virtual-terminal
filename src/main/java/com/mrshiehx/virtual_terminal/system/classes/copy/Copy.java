package com.mrshiehx.virtual_terminal.system.classes.copy;

import com.mrshiehx.virtual_terminal.system.classes.Command;

public class Copy extends AbstractCopy implements Command {
    String[] getStrings() {
        return new String[]{
                "Failed to copy the file \"%s\"\n",
                "Failed to copy the file \"%s\", the error is: %s\n",
                "There is a file failed to copy. It's %s\n",
                "There are %d files failed to copy. They are: \n",
                "Failed to copy the directory \"%s\", the error is: %s\n"};
    }
}
