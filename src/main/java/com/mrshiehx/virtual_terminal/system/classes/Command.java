package com.mrshiehx.virtual_terminal.system.classes;

public interface Command {
    void main(String[] args);

    void usage(String command);
}
