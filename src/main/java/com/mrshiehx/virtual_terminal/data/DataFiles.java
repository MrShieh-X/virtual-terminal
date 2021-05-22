package com.mrshiehx.virtual_terminal.data;

import com.mrshiehx.virtual_terminal.constants.Extensions;
import com.mrshiehx.virtual_terminal.utils.command.CommandFileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.mrshiehx.virtual_terminal.data.DataDirectories.*;

public class DataFiles {
    private DataFiles() {
    }

    public static File getUserFile(String name) {
        return new File(OBJECTS_USERS, CommandFileUtils.clearDoubleQuotationMarks(name) + Extensions.USER);
    }

    public static File getErrorLogFile() {
        return new File(TERMINAL_ERROR_LOGS, new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".log");
    }
}
