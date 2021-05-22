package com.mrshiehx.virtual_terminal.utils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private FileUtils() {
    }

    public static byte[] getBytes(File file) throws IOException {
        FileChannel fc = new RandomAccessFile(file, "r").getChannel();
        MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                fc.size()).load();
        byte[] result = new byte[(int) fc.size()];
        if (byteBuffer.remaining() > 0) {
            byteBuffer.get(result, 0, byteBuffer.remaining());
        }
        IoStreamUtils.close(fc);
        return result;
    }

    public static String getString(File target) throws IOException {
        FileInputStream s = new FileInputStream(target);
        String b = IoStreamUtils.getString(s);
        try {
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public static void deleteDirectory(File directory) {
        if (directory != null) {
            if (directory.exists()) {
                if (directory.isDirectory()) {
                    if (directory.listFiles() != null) {
                        if (directory.listFiles().length != 0) {
                            File[] files = directory.listFiles();
                            for (File file : files) {
                                if (file.isFile()) {
                                    file.delete();
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

    public static void copy(File source, File to) throws IOException {
        InputStream input = new FileInputStream(source);
        OutputStream output = new FileOutputStream(to);
        byte[] buf = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, bytesRead);
        }
        input.close();
        output.close();
    }

    public static boolean allreplace, allskip;

    public static int ros(String path) {
        if ((allreplace == false && allskip == false) || (allreplace == true && allskip == true)) {
            System.out.printf("The target directory \"%s\" already exists, (R)eplace, (AR)All Replace, (S)kip or (AS)All Skip)?", path);
            switch (new Scanner(System.in).nextLine().toLowerCase()) {
                case "r":
                case "replace":
                    return 0;
                case "s":
                case "skip":
                    return 1;
                case "ar":
                case "allreplace":
                    allreplace = true;
                    allskip = false;
                    return 0;
                case "as":
                case "allskip":
                    allskip = true;
                    allreplace = false;
                    return 1;
                default:
                    return ros(path);
            }
        } else {
            if (allreplace) return 0;
            if (allskip) return 1;
            return ros(path);
        }
    }

    public static void copyDirectory(File from, String toWillNewDirNameIsAtFromName, String afterThatName, List<String> failed) {
        if (from != null && !StringUtils.isEmpty(toWillNewDirNameIsAtFromName) && from.exists()) {
            File toWillNewDirNameIsAtFrom = new File(toWillNewDirNameIsAtFromName);
            File to = new File(toWillNewDirNameIsAtFrom, afterThatName);
            if (!to.exists()) {
                to.mkdirs();
                if (from.listFiles() != null && from.listFiles().length > 0) {
                    for (File file : from.listFiles()) {
                        if (file.isFile()) {
                            //File var0 = new File(to, file.getName());
                            try {
                                copy(file, new File(to, file.getName()));
                            } catch (IOException e) {
                                try {
                                    failed.add(file.getCanonicalPath());
                                } catch (Exception exxx) {
                                    failed.add(file.getAbsolutePath());
                                }
                            }
                        } else {
                            copyDirectory(file, to.getAbsolutePath(), file.getName(), failed);
                        }
                    }
                }
            } else {
                String path;
                try {
                    path = to.getCanonicalPath();
                } catch (Exception e) {
                    path = to.getAbsolutePath();
                }
                if (ros(path) == 0) {
                    if (from.listFiles() != null && from.listFiles().length > 0) {
                        for (File file : from.listFiles()) {
                            if (file.isFile()) {
                                //File var0 = new File(to, file.getName());
                                try {
                                    copy(file, new File(to, file.getName()));
                                } catch (IOException e) {
                                    try {
                                        failed.add(file.getCanonicalPath());
                                    } catch (Exception exxx) {
                                        failed.add(file.getAbsolutePath());
                                    }
                                }
                            } else {
                                copyDirectory(file, to.getAbsolutePath(), file.getName(), failed);
                            }
                        }
                    }
                }
            }
        }
    }
}
