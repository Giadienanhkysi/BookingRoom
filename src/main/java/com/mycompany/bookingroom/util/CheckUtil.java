package com.mycompany.bookingroom.util;

public class CheckUtil {
    public static boolean isInteger(String number){
        try{
            Integer.parseInt(number);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
        
    }
}
