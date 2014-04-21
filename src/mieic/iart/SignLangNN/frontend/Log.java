package mieic.iart.SignLangNN.frontend;

import java.sql.Timestamp;

/**
 * Created by knoweat on 14/04/14.
 */
public class Log {
    public static boolean PRINT_LOGS = true;

    public static void log(String messg) {
        if (PRINT_LOGS) {
            java.util.Date date = new java.util.Date();
            System.out.println(new Timestamp(date.getTime()) + "=>" + messg);
        }
    }
}
