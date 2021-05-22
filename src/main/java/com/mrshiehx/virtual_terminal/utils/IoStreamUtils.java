package com.mrshiehx.virtual_terminal.utils;

import java.io.*;

public class IoStreamUtils {
    private IoStreamUtils() {
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {

        }
    }

    public static String getString(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        //inputStream.close();
        inputStreamReader.close();
        reader.close();
        return sb.toString();
    }
}