package com.dwijantojohan.lie.picmicrocontroller;

import java.io.IOError;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by dlie on 5/13/2016.
 * Helper for all activities
 * Test Commit to github
 */
public class  Helper {
    public static Object result=null;
    public static boolean validateText(String str){
        boolean myret = false;

        try {
            int length = str.length();
           if (str.trim().toLowerCase().contains("0x")){
                //Hex Value
                if (tryParseIntHex(str.trim().substring(2))){
                    result = Integer.parseInt(str.trim().substring(2),16);
                    myret=true;
                }
            }else if(str.trim().toLowerCase().contains("mhz"))
            {
                //Mhz value
                if (tryParseInt(str.trim().substring(0,length-3))){
                    result = Integer.parseInt(str.trim().substring(0,length-3));
                    myret=true;
                }
            }else if(str.trim().toLowerCase().contains("khz")){
                //KHz value
                if (tryParseInt(str.trim().substring(0,length-3))){
                    result = Integer.parseInt(str.trim().substring(0,length-3));
                    myret=true;
                }
            }else if(str.trim().toLowerCase().contains("hz")){
                //Hz value
                if (tryParseInt(str.trim().substring(0,length-2))){
                    result = Integer.parseInt(str.trim().substring(0,length-3));
                    myret=true;
                }
            }else if(str.trim().contains(".")){
                //float value

                result = Float.parseFloat(str.trim());
                myret=true;
            }else {
                //int value
                if(tryParseInt(str.trim())){
                    result = Integer.parseInt(str.trim());
                    myret=true;
                }

            }
        }catch (IOError e){

        }




        return myret;
    }

    public static boolean tryParseIntHex(String value){
        try{
            Integer.parseInt(value,16);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean tryParseInt(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean tryParseFloat(String value){
        try{
            Float.parseFloat(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public static int getValueHz(int value,String str){
        switch (str.toLowerCase()){
            case "mhz":
                value *= 1000000;
                break;
            case "khz":
                value *= 1000;
                break;
        }
        return value;
    }
    /*
    preload integer
    clock integer HZ
    prescaller integer
    resolution integer
    */

    public static String getTout(int preload,int clock,int prescaller,int resolution){
        double result=0;
        int delay = resolution - preload;
        double f = clock/(4*prescaller*delay);
        BigDecimal fout = BigDecimal.valueOf(f) ; //in HZ
        BigDecimal tout = BigDecimal.valueOf(1/f); //in sec

        return timeConversion(tout);
    }

    public static String getPreload(double period,int clock,int prescaller,int resolution){
        BigDecimal fout = BigDecimal.valueOf(1/period);
        BigDecimal preload = BigDecimal.valueOf(clock/(4*prescaller*(1/period)));
        return "";
    }

    /*
     value float in second
     */

    public static String timeConversion(BigDecimal value) {
        String result = "";
        String timeUnit = "sec";
        BigDecimal tmp = value;
        int cp = tmp.compareTo(BigDecimal.ONE);

        if (cp >= 0) {
            value = tmp.divide(BigDecimal.valueOf(60));
            cp=value.compareTo(BigDecimal.ONE);
            if (cp>=0){
                timeUnit = "min";
            }else{
                value = tmp.multiply(BigDecimal.valueOf(60));
                timeUnit = "sec";
            }
        }else{
            value = tmp.multiply(BigDecimal.valueOf(1000));
            cp = value.compareTo(BigDecimal.ONE);
            if (cp >= 0) {
                timeUnit = "ms";
            } else {
                value = tmp.multiply(BigDecimal.valueOf(1000000));
                cp = value.compareTo(BigDecimal.ONE);
                if (cp >= 0) {
                    timeUnit = "us";
                } else {
                    value = tmp.multiply(BigDecimal.ONE.valueOf(1000000000));
                    cp = value.compareTo(BigDecimal.ONE);
                    if (cp >= 0) {
                        timeUnit = "ns";
                    }
                }
            }
        }
        return String.format("%.01f %s ", value, timeUnit);
    }
}

