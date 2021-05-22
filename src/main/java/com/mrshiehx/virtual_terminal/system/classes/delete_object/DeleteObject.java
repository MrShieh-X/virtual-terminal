package com.mrshiehx.virtual_terminal.system.classes.delete_object;

import com.mrshiehx.virtual_terminal.system.classes.Command;
import com.mrshiehx.virtual_terminal.system.classes.create_object.CreateObject;

public class DeleteObject implements Command {
    @Override
    public void main(String[] args) {
        if (args.length < 2) {
            usage(args[0]);
        } else {
            String type = args[1];
            if (type.equals("/?") || type.equals("/help")) {
                usage(args[0]);
            } else if (type.equals("/types") || type.equals("/type")) {
                CreateObject.types();
            } else {
                if (type.equals("-u") || type.equals("--u") || type.equals("-user") || type.equals("--user")) {
                    new DeleteUser().main(args);
                } else {
                    CreateObject.typeNotFound(type);
                }
            }
        }
    }

    @Override
    public void usage(String command) {
        System.out.println("Usage: " + command + " <object type> <some arguments...>: Deletes an existed object.");
        System.out.println("       " + command + " /types: Lists all types.");
    }
}
