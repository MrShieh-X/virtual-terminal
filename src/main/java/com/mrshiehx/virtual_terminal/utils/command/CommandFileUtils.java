package com.mrshiehx.virtual_terminal.utils.command;

import com.mrshiehx.virtual_terminal.VirtualTerminal;
import com.mrshiehx.virtual_terminal.system.RootDirectories;

import java.io.File;

public class CommandFileUtils {
    public static File getFile(String filePath) {
        return filePath.replace('\\', '/')
                .startsWith("/")
                || filePath.replace('\\', '/')
                .substring(1).startsWith(":/")
                ? new File(RootDirectories.ROOT, formatPath(filePath))
                : new File(VirtualTerminal.currentDirectory, formatPath(filePath));
    }

    public static String formatPath(String path) {
        path = clearDoubleQuotationMarks(path.replace('\\', '/'));
        if (path.contains("/")) {
            String[] split = path.split("/");
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : split) {
                stringBuilder.append(clearDoubleQuotationMarks(str)).append('/');
            }
            return stringBuilder.toString();
        }
        return path;
    }

    public static String clearDoubleQuotationMarks(String path) {
        String qm = "\"";
        if (path.startsWith(qm)) {
            path = path.substring(qm.length());
        }
        if (path.endsWith(qm)) {
            path = path.substring(0, path.length() - qm.length());
        }
        return path;
    }

    public static void printsFileExisted(String path) {
        System.out.printf("%s: The file is existed.\n", path);
    }

    public static void printsFileNotExist(String path) {
        System.out.printf("%s: The file does not exist.\n", path);
    }

    public static void printsNotDirectory(String path) {
        System.out.printf("%s: Not a directory.\n", path);
    }

    public static void printsNotFile(String path) {
        System.out.printf("%s: Not a file.\n", path);
    }
}
