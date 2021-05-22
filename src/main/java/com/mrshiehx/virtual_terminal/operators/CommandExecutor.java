package com.mrshiehx.virtual_terminal.operators;

import com.mrshiehx.virtual_terminal.data.DataDirectories;
import com.mrshiehx.virtual_terminal.system.SystemDirectories;
import com.mrshiehx.virtual_terminal.system.classes.*;
import com.mrshiehx.virtual_terminal.system.classes.copy.*;
import com.mrshiehx.virtual_terminal.system.classes.create_object.*;
import com.mrshiehx.virtual_terminal.system.classes.delete_object.DeleteObject;
import com.mrshiehx.virtual_terminal.system.classes.make_directory.MakeDirectories;
import com.mrshiehx.virtual_terminal.system.classes.make_directory.MakeDirectory;
import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private CommandExecutor() {
    }

    public static final Map<String, Command> COMMANDS;

    static {
        COMMANDS = new HashMap<>();
        COMMANDS.put("ls", new List());
        COMMANDS.put("exit", new Exit());
        COMMANDS.put("echo", new Echo());
        COMMANDS.put("cd", new ChangeDirectory());
        COMMANDS.put("su", new SwitchUser());
        COMMANDS.put("co", new CreateObject());
        COMMANDS.put("cf", new CreateFile());
        COMMANDS.put("mkdir", new MakeDirectory());
        COMMANDS.put("mkdirs", new MakeDirectories());
        COMMANDS.put("do", new DeleteObject());
        COMMANDS.put("df", new DeleteFile());
        COMMANDS.put("cp", new Copy());
        COMMANDS.put("rn", new Rename());
        COMMANDS.put("mv", new Move());
    }

    /*private static void putCommandClasses(){
        List<String>keys=new ArrayList<>(COMMAND_STRINGS.keySet());
        List<String>values=new ArrayList<>(COMMAND_STRINGS.values());
        for(int i=0;i<COMMAND_STRINGS.size();i++){
            try{
                putCommand(keys.get(i), values.get(i));
            }catch (ClassNotFoundException e){
                
            }
        }
    }

    private static void putCommand(String command, String className)throws ClassNotFoundException{
        COMMANDS.put(command,getCommandClass(className));
    }*/

    public static void execute(String[] arguments) {
        if (arguments.length > 0) {
            String command = arguments[0];
            Command clazz = COMMANDS.get(command.toLowerCase());
            if (clazz != null) {
                /*try {
                    executeMainMethod(clazz, arguments);
                }catch (Exception e){
                    
                    System.out.printf("%s: No such command or failed to execute. The error is: %s\n",command, e.toString());
                }*/
                if (arguments.length >= 2 && (arguments[1].equals("/?") || arguments[1].equals("/help"))) {
                    clazz.usage(arguments[0]);
                } else
                    clazz.main(arguments);
            } else {
                File file = new File(SystemDirectories.CLASSES, command);
                if ((file.exists() && file.isFile()) || ((file = new File(DataDirectories.CLASSES, command)).exists() && file.isFile()) || ((file = CommandFileUtils.getFile(command)).exists() && file.isFile())) {
                    if (file.isDirectory()) {
                        file = new File(file, command);
                        if (!file.exists()) {
                            System.out.printf("%s: No such command\n", command);
                            return;
                        }
                    }
                    try {
                        String realco = command;
                        if (command.replace('\\', '/').contains("/")) {
                            realco = command.substring(command.lastIndexOf('/') + 1);
                        }
                        Object loadClass = new FileClassLoader(file).loadFile(realco).getConstructor().newInstance();
                        Method[] methods = loadClass.getClass().getMethods();
                        Method main = null, usage = null;
                        for (Method method : methods) {
                            if (method.getParameterTypes().length == 1 && Arrays.equals(method.getParameterTypes(), new Class[]{String[].class}) && method.getName().equals("main")) {
                                main = method;
                            } else if (method.getParameterTypes().length == 1 && Arrays.equals(method.getParameterTypes(), new Class[]{String.class}) && method.getName().equals("usage")) {
                                usage = method;
                            }
                            if (main != null && usage != null) break;
                        }

                        //Method method = /*loadClass.getMethod("main",String[].class);VirtualTerminal.currentUser.getClass()*/loadClass.getClass().getMethod("main", String[].class);

                        //Method methodU = /*loadClass.getMethod("main",String[].class);VirtualTerminal.currentUser.getClass()*/loadClass.getClass().getMethod("usage");

                        if (arguments.length >= 2 && (arguments[1].equals("/?") || arguments[1].equals("/help"))) {
                            //loadClass.usage(args[0]);
                            //methodU.invoke(loadClass);
                            if (usage != null) {
                                usage.invoke(loadClass, arguments[0]);
                            } else {
                                if (main != null) {
                                    main.invoke(loadClass, (Object) arguments);
                                } else {
                                    System.out.printf("Although the command \"%s\" exists, but the parameter is only String[] and the method named \"main\" does not exist.\n", realco);
                                }
                            }
                        } else {
                            //loadClass.main(arguments);
                            //method.invoke(loadClass, (Object) arguments);
                            if (main != null) {
                                main.invoke(loadClass, (Object) arguments);
                            } else {
                                System.out.printf("Although the command \"%s\" exists, but the parameter is only String[] and the method named \"main\" does not exist.\n", realco);
                            }
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        System.out.printf("%s: No such command or failed to execute. The error is: %s\n", command, e.toString());
                    }
                } else {
                    System.out.printf("%s: No such command\n", command);
                }
            }
        }
    }

    /*public static Class<?>getCommandClass(String name) throws ClassNotFoundException {
        return Class.forName("com.mrshiehx.virtual_terminal.system.classes."+name);
    }

    public static Object executeMainMethod(Class<?>clazz,String[]args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method method=clazz.getMethod("main",String[].class);
        return method.invoke(clazz.newInstance(), (Object) args);
    }*/
}
