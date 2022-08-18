package com.sonu.urlStorage.util;

public class UniqueShortKeyGenerator {
    public static String UniqueShortKey(long id){
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuffer shorturl = new StringBuffer();

        while (id > 0)
        {
            shorturl.append(map[(int) (id % 62)]);
            id = id / 62;
        }
        return shorturl.reverse().toString();
    }

}
