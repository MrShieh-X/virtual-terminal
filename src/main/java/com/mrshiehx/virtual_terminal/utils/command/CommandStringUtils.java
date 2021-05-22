package com.mrshiehx.virtual_terminal.utils.command;

import com.mrshiehx.virtual_terminal.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandStringUtils {
    private CommandStringUtils() {
    }

    public static String format(String command) {
        return StringUtils.isNotEmpty(command) ? clearRedundantSpaces(command.trim()) : "";
    }

    /*public static String replaceSpaceToEmpty(String str){
        char[]source=str.toCharArray();
        char[]newa=new char[str.length()];
        for(int i=0;i<source.length;i++){
            char a=source[i];
            newa[i]=a==' '?0:a;
        }
        return new String(newa);
    }*/

    public static String clearRedundantSpacesOld(String str) {
        return str.replaceAll("\\s+", " ");
    }

    public static char[] removeChar(char[] chars, int index) {
        char[] mJsonArray = new char[chars.length - 1];
        if (index < 0)
            return chars;

        if (index > chars.length)
            return chars;

        System.arraycopy(chars, 0, mJsonArray, 0, index);

        if (chars.length - (index + 1) >= 0)
            System.arraycopy(chars, index + 1, mJsonArray, index + 1 - 1, chars.length - (index + 1));
        return mJsonArray;
    }


    public static String clearRedundantSpaces(String string) {
        char[] sourceChars = string.toCharArray();
        Object space = new Object();
        Object[] objects = new Object[string.length()];
        boolean yinyong = false;
        for (int i = 0; i < sourceChars.length; i++) {
            char cha = sourceChars[i];
            if (cha == '\"') {
                yinyong = !yinyong;
            }
            objects[i] = !yinyong && cha == ' ' ? space : cha;
        }
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            Object object = objects[i];
            if (object == space) {
                list.add(' ');
                for (int j = i; j < objects.length; j++) {
                    if (objects[j] != space) {
                        i = j - 1;
                        break;
                    }
                }

            } else if (object instanceof Character) {
                list.add((Character) object);
            }
        }

        char[] chars = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            chars[i] = list.get(i);
        }
        return new String(chars);
    }

    /*public static String[] split(String src, char symbol){
        List<String>list=new ArrayList<>();
        boolean yinyong=false;
        char[]chars=src.toCharArray();
        for(int i=0;;){
            char c=chars[i];
            if(c=='\"')yinyong=!yinyong;
            if(!yinyong){
                String newS=src.substring(i);
                int nextSpace=newS.indexOf(' ');
                StringBuilder stringBuilder=new StringBuilder();
                int nextSpaceInSource=nextSpace+i;

            }
        }
        return list.toArray(new String[0]);
    }*/

    public static String[] split(String src) {
        List<String> list = new ArrayList<>();
        /*List<Boolean> yinyongs=new ArrayList<>();
        String[]split=src.split(String.valueOf(symbol));*/
        boolean yinyong = false;
        for (int i = 0; i < src.length(); i++) {
            char str = src.charAt(i);
            if (str == '\"') yinyong = !yinyong;
            if (!yinyong) {
                if (str != ' ') {
                    if (i == 0) list.add(String.valueOf(str));
                    else list.set(list.size() - 1, list.get(list.size() - 1) + str);
                } else {
                    list.add("");
                }
            } else {
                list.set(list.size() - 1, list.get(list.size() - 1) + str);
            }
        }
        return list.toArray(new String[0]);
    }
}
