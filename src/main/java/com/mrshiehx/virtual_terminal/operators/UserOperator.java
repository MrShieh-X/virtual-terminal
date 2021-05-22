package com.mrshiehx.virtual_terminal.operators;

import com.mrshiehx.virtual_terminal.objects.User;

import java.io.*;

public class UserOperator {
    private UserOperator() {
    }

    /*public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.print("Read(0) or write(1): ");
        switch (new Scanner(System.in).nextLine()) {
            case "1":
                write();
                break;
            case "0":
                read();
                break;
        }
    }

    public static void write() throws IOException {
        String name;
        String displayName;
        String permissions;
        File file;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        name = scanner.nextLine();
        Scanner scanner4 = new Scanner(System.in);
        System.out.print("Display Name: ");
        displayName = scanner4.nextLine();
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Permissions: ");
        permissions = scanner2.nextLine();
        Scanner scanner3 = new Scanner(System.in);
        System.out.print("Target File: ");
        file = new File(scanner3.nextLine());
        User user = new User();
        user.userName = name;
        user.userPermissions = permissions;
        user.userDisplayName = displayName;
        write(user, file);
    }

    public static void read() throws IOException, ClassNotFoundException {
        File file;
        System.out.print("Target File: ");
        file = new File(new Scanner(System.in).nextLine());
        User user = read(file);
        System.out.printf("Name: %s, Display Name: %s, Permissions: %s\n", user.userName, user.userDisplayName, user.userPermissions);
    }*/

    public static User read(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User) ois.readObject();
        fis.close();
        ois.close();
        return user;
    }

    public static void write(User user, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        oos.flush();
        oos.close();
    }
}
