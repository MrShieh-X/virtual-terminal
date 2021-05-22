package com.mrshiehx.virtual_terminal.system.classes.create_object;

import com.mrshiehx.virtual_terminal.data.DataFiles;
import com.mrshiehx.virtual_terminal.objects.User;
import com.mrshiehx.virtual_terminal.operators.UserOperator;
import com.mrshiehx.virtual_terminal.system.classes.Command;
import com.mrshiehx.virtual_terminal.utils.StringUtils;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.mrshiehx.virtual_terminal.utils.JOptSimpleUtils.getValue;

public class CreateUser implements Command {
    public void main(String[] args) {
        if (args.length >= 3 && (args[2].equals("/?") || args[2].equals("/help"))) usage(args[0]);
        else {
            String[] newArgs = new String[args.length - 1];
            System.arraycopy(args, 1, newArgs, 0, args.length - 1);

            OptionParser optionparser = new OptionParser();
            optionparser.allowsUnrecognizedOptions();

            OptionSpec<String> nameOS = optionparser.accepts("n").withRequiredArg().ofType(String.class);
            OptionSpec<String> displayNameOS = optionparser.accepts("dn").withRequiredArg().ofType(String.class);
            //OptionSpec<String> perOS = optionparser.accepts("p").withRequiredArg().ofType(String.class);

            OptionSpec<String> nameOSL = optionparser.accepts("name").withRequiredArg().ofType(String.class);
            OptionSpec<String> displayNameOSL = optionparser.accepts("displayName").withRequiredArg().ofType(String.class);
            //OptionSpec<String> perOSL = optionparser.accepts("permissions").withRequiredArg().ofType(String.class);

            OptionSet optionSet = optionparser.parse(newArgs);

            String name = null;
            String displayName = null;

            if (optionSet.has(nameOS)) {
                name = getValue(optionSet, nameOS);
            } else if (optionSet.has(nameOSL)) {
                name = getValue(optionSet, nameOSL);
            }

            if (optionSet.has(displayNameOS)) {
                displayName = getValue(optionSet, displayNameOS);
            } else if (optionSet.has(displayNameOSL)) {
                displayName = getValue(optionSet, displayNameOSL);
            }

            if (StringUtils.isEmpty(name)) {
                System.out.print("Name: ");
                Scanner scanner = new Scanner(System.in);
                name = scanner.nextLine();
            }

            if (StringUtils.isEmpty(displayName)) {
                System.out.print("Display Name: ");
                Scanner scanner = new Scanner(System.in);
                displayName = scanner.nextLine();
            }

            if (StringUtils.isEmpty(name)) {
                System.out.println("The name can't be null.");
                return;
            }
            if (StringUtils.isEmpty(displayName)) {
                System.out.println("The display name can't be null.");
                return;
            }

            User user = new User();
            user.userName = name;
            user.userDisplayName = displayName;
            user.userPermissions = "01";
            File file = DataFiles.getUserFile(name);
            if (file.exists()) {
                if (exists(name) == 0) {
                    file.delete();
                    write(user, file);
                }
            } else write(user, file);
        }
    }

    private void write(User user, File file) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            UserOperator.write(user, file);
            //System.out.printf("Successfully created user \"%s\".\n", user.userName);
        } catch (IOException e) {
            System.out.printf("Failed to create user \"%s\", the error is: %s\n", user.userName, e.toString());
        }
    }

    private int exists(String name) {
        System.out.printf("User \"%s\" exists, (O)verride or (C)ancel the operation?", name);
        switch (new Scanner(System.in).nextLine().toLowerCase()) {
            case "o":
            case "override":
                return 0;
            case "c":
            case "cancel":
                return 1;
            default:
                return exists(name);
        }
    }

    public void usage(String command) {
        System.out.println("Usage: " + command + " -u -n <name> -dn <display name>: Creates an user.");
    }
}
