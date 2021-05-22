package com.mrshiehx.virtual_terminal.utils;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.util.List;

public class JOptSimpleUtils {
    public static <T> T getValue(OptionSet set, OptionSpec<T> option) {
        try {
            return set.valueOf(option);
        } catch (Throwable throwable) {
            if (option instanceof ArgumentAcceptingOptionSpec) {
                List<T> list = ((ArgumentAcceptingOptionSpec) option).defaultValues();
                if (!list.isEmpty()) {
                    return list.get(0);
                }
            }
            throw throwable;
        }
    }
}
