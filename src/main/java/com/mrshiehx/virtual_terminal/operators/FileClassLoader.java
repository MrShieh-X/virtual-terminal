package com.mrshiehx.virtual_terminal.operators;

import com.mrshiehx.virtual_terminal.utils.ByteUtils;
import com.mrshiehx.virtual_terminal.utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileClassLoader extends ClassLoader {
    private final File classFile;

    public FileClassLoader(File classFile) {
        this.classFile = classFile;
    }

    public Class<?> loadFile(String name) throws ClassNotFoundException {
        synchronized (this) {
            Class<?> findClass = findClass(name);
            if (findClass != null) {
                return findClass;
            }
        }
        return loadClass(name);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classByte;
        if (!classFile.exists()) {
            throw new ClassNotFoundException("class file does not exist: " + name);
        }
        if (classFile.isDirectory()) {
            throw new ClassNotFoundException("class file is a directory: " + name);
        }
        try {
            classByte = FileUtils.getBytes(classFile);
        } catch (IOException e) {
            throw new ClassNotFoundException(String.format("failed to load the class: %s, the error is: %s", name, e.toString()));
        }
        if (classByte.length == 0 || ByteUtils.isBytesAllZero(classByte)) {
            throw new ClassNotFoundException(String.format("failed to load the class: %s, the content of the class is empty", name));
        }
        return this.defineClass(name, classByte, 0, classByte.length);
    }
}