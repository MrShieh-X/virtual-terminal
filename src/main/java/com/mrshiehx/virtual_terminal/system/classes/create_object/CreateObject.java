package com.mrshiehx.virtual_terminal.system.classes.create_object;

import com.mrshiehx.virtual_terminal.system.classes.Command;

public class CreateObject implements Command {
    public void main(String[] args) {
        if (args.length < 2) {
            usage(args[0]);
        } else {
            String type = args[1];
            if (type.equals("/?") || type.equals("/help")) {
                usage(args[0]);
            } else if (type.equals("/types") || type.equals("/type")) {
                types();
            } else {
                if (type.equals("-u") || type.equals("--u") || type.equals("-user") || type.equals("--user")) {
                    new CreateUser().main(args);
                } else {
                    typeNotFound(type);
                }
            }
        }

    }

    public static void typeNotFound(String name) {
        System.out.printf("%s: Type not found.\n", name);
    }

    public void usage(String command) {
        System.out.println("Usage: " + command + " <object type> <some arguments...>: Creates an object.");
        System.out.println("       co /types: Lists all types.");
    }

    public static void types() {
        System.out.println("-u: User");
    }
}
