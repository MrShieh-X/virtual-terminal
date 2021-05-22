package com.mrshiehx.virtual_terminal;

import com.mrshiehx.virtual_terminal.data.DataDirectories;
import com.mrshiehx.virtual_terminal.data.DataFiles;
import com.mrshiehx.virtual_terminal.exceptions.CreateUserException;
import com.mrshiehx.virtual_terminal.exceptions.LoadUserException;
import com.mrshiehx.virtual_terminal.exceptions.NotADirectoryException;
import com.mrshiehx.virtual_terminal.exceptions.UserNotFoundException;
import com.mrshiehx.virtual_terminal.objects.User;
import com.mrshiehx.virtual_terminal.operators.CommandExecutor;
import com.mrshiehx.virtual_terminal.operators.UserOperator;
import com.mrshiehx.virtual_terminal.system.RootDirectories;
import com.mrshiehx.virtual_terminal.system.SystemDirectories;
import com.mrshiehx.virtual_terminal.system.SystemFiles;
import com.mrshiehx.virtual_terminal.utils.command.CommandStringUtils;
import com.mrshiehx.virtual_terminal.getters.DeviceInformationGetter;
import com.mrshiehx.virtual_terminal.utils.StringUtils;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.mrshiehx.virtual_terminal.utils.JOptSimpleUtils.getValue;

public class VirtualTerminal {
    public static File currentDirectory = new File(".");
    public static User currentUser;
    public static boolean echoOpen = true;
    public static final User defaultUser;

    static {
        defaultUser = new User();
        defaultUser.userName = "virterm";
        defaultUser.userDisplayName = "VirTerm";
        defaultUser.userPermissions = "01";
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("------------------- Welcome to Virtual Terminal 1.0 -------------------");
        OptionParser optionparser = new OptionParser();
        optionparser.allowsUnrecognizedOptions();
        OptionSpec<String> userOptionSpec = optionparser.accepts("user").withRequiredArg().ofType(String.class).defaultsTo(defaultUser.userName);
        OptionSpec<File> rootPathOptionSpec = optionparser.accepts("root").withRequiredArg().ofType(File.class).defaultsTo(new File("/"));
        OptionSet optionSet = optionparser.parse(args);
        String user = getValue(optionSet, userOptionSpec);
        File file = getValue(optionSet, rootPathOptionSpec);
        if (!file.exists()) file.mkdirs();
        else if (file.isFile()) throw new NotADirectoryException(file.getAbsolutePath() + " is a file");
        RootDirectories.ROOT = file;
        SystemDirectories.init();
        SystemFiles.init();
        DataDirectories.init();
        /**try {
         File file2=DataFiles.getErrorLogFile();
         file2.getParentFile().mkdirs();
         if (file2.exists()) {
         file2.delete();
         }
         file2.createNewFile();
         System.setErr(new PrintStream(file2));
         }catch (IOException e){

         }*/
        if (!user.equals(defaultUser.userName)) {
            File userFile = DataFiles.getUserFile(user);
            if (!userFile.exists()) throw new UserNotFoundException("user not found: " + user);
            try {
                currentUser = UserOperator.read(userFile);
            } catch (Exception e) {

                throw new LoadUserException(String.format("failed to load the user \"%s\", the error is: %s", user, e.toString()));
            }
        } else {
            currentUser = getDefaultUser();
        }

        while (true) {
            if (echoOpen) {
                printPrompt();
            }
            String command;
            Scanner scanner = new Scanner(System.in);
            String s;
            try {
                s = scanner.nextLine();
            } catch (NoSuchElementException e) {
                return;
            }
            if (StringUtils.isNotEmpty(s)) {
                command = CommandStringUtils.format(s);
                if (command.equals("echo off")) {
                    echoOpen = false;
                } else if (command.equals("echo on")) {
                    echoOpen = true;
                } else {
                    String[] arguments;
                    if (!s.startsWith("echo ")) {
                        if (command.contains(" ")) {
                            arguments = CommandStringUtils.split(command);
                        } else {
                            arguments = new String[]{command};
                        }
                    } else {
                        if (s.length() == "echo".length() + 1) {
                            arguments = new String[]{"echo"};
                        } else if (command.startsWith("echo -f") || command.startsWith("echo --f") || command.startsWith("echo -file") || command.startsWith("echo --file")) {
                            arguments = CommandStringUtils.split(command);
                        } else {
                            arguments = new String[]{"echo", s.substring("echo".length() + 1)};
                        }
                    }
                    //List.main(arguments);
                    CommandExecutor.execute(arguments);
                }
            }
        }
    }

    public static User getDefaultUser() {
        File file = DataFiles.getUserFile(defaultUser.userName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                UserOperator.write(defaultUser, file);
            } catch (IOException e) {

                throw new CreateUserException("failed to create the default user, the error is: " + e);
            }
        }
        return defaultUser;
    }

    public static void printPrompt() {
        System.out.print(currentUser.userName);
        System.out.print("@");
        System.out.print(DeviceInformationGetter.getDeviceName());
        System.out.print(":");
        try {
            System.out.print(currentDirectory.getCanonicalPath());
        } catch (IOException e) {
            System.out.print(currentDirectory.getAbsolutePath());
        }
        System.out.print("$ ");
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static File getCurrentDirectory() {
        return currentDirectory;
    }

    public static boolean isEchoOpen() {
        return echoOpen;
    }
}
