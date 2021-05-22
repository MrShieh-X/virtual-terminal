package com.mrshiehx.virtual_terminal.system.classes;

import com.mrshiehx.virtual_terminal.utils.StringUtils;

public class Exit implements Command {
    public void main(String[] args) {
        if (args.length >= 2) {
            if (args[1].equals("/?") || args[1].equals("/help")) {
                usage(args[0]);
            } else {
                int status = 0;
                if (StringUtils.isNumber(args[1])) {
                    try {
                        status = Integer.parseInt(args[1]);
                    } catch (Throwable ignored) {
                    }
                }
                System.exit(status);
            }
        } else {
            System.exit(0);
        }
    }

    public void usage(String command) {
        System.out.println("Usage: " + command + ": Exits the program with exit code 0.");
        System.out.println("       " + command + " <exit code>: Exits the program with an exit code.");
    }
}
