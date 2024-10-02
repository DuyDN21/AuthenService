package com.authentication.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashingUtil {

    protected static final int LOG_ROUNDS = 10;

    public static String HashPassword(String password, String salt){
        return BCrypt.hashpw(password, salt);
    }

    public static boolean CheckPassword(String input, String password){
        return BCrypt.checkpw(input, password);
    }

    public static String GenerateSalt(){
        return BCrypt.gensalt(LOG_ROUNDS);
    }
}

