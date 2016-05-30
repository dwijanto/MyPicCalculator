package com.dwijantojohan.lie.picmicrocontroller;

import java.io.IOError;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.math.RoundingMode;

/**
 * Created by dlie on 5/13/2016.
 * Helper for all activities
 * Test Commit to github
 */
public class  Helper {
    public static Object result=null;
    public static double ToutResult;

    public static boolean validateText(String str){
        boolean myret = false;
        try {
            int length = str.trim().length();
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
            }else if(str.trim().toLowerCase().contains("hz")) {
               //Hz value
               if (tryParseInt(str.trim().substring(0, length - 2))) {
                   result = Integer.parseInt(str.trim().substring(0, length - 2));
                   myret = true;
               }
            }else if(str.trim().toLowerCase().contains("ns")) {
               if (tryParseDouble(str.trim().substring(0, length - 2))) {
                   result = Double.parseDouble(str.trim().substring(0, length - 2))/1000000000;
                   myret=true;
               }
            }else if(str.trim().toLowerCase().contains("us")) {
               if (tryParseDouble(str.trim().substring(0, length - 2))) {
                   result =Double.parseDouble(str.trim().substring(0, length - 2))/1000000;
                   myret=true;
               }
           }else if(str.trim().toLowerCase().contains("ms")) {
               if (tryParseDouble(str.trim().substring(0, length - 2))) {
                   result = Double.parseDouble(str.trim().substring(0, length - 2))/1000;
                   myret=true;
               }
           }else if(str.trim().toLowerCase().contains("sec")) {
               if (tryParseDouble(str.trim().substring(0, length - 3))) {
                   result = Double.parseDouble(str.trim().substring(0, length - 3));
                   myret=true;
               }
           }else if(str.trim().toLowerCase().contains("min")) {
               if (tryParseDouble(str.trim().substring(0, length - 3))) {
                   result = Double.parseDouble(str.trim().substring(0, length - 3))*60;
                   myret=true;
               }
           }else if(str.trim().contains(".")){
                //Double value
                if (tryParseDouble(str.trim())){
                    result = Double.parseDouble(str.trim());
                    myret=true;
                }

            }else {
                //int value
                if(tryParseInt(str.trim())){
                    result = Integer.parseInt(str.trim());
                    myret=true;
                }

            }
        }catch (IOError e){
            myret = false;
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

    public static boolean tryParseDouble(String value){
        try{
            Double.parseDouble(value);
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

    public static String getTout(int preload,int clock,int prescaller,int resolution,int clockSource){
        double result=0;
        int delay = resolution - preload;
        double f = (double) clock/(clockSource*prescaller*delay);
        double tout = 1/f;
        ToutResult  = tout;

        return timeConversion(tout);
    }

    public static int getPreload(double period,int clock,int prescaller,int resolution,int clockSource){
        int myret;
        int preload;
        preload = (int)Math.round((clock /(clockSource*prescaller*(1/period))));
        myret = resolution - preload;
        return (myret <0)?0:myret;
    }

    public static String timeConversion(double value) {
        String timeUnit = "sec";
        double tmp = value;
        if (tmp >= 1) {
            value = tmp / 60;
            if (value >=1 ){
                timeUnit = "min";
            }else{
                value = tmp;
                timeUnit= "sec";
            }
        }else{
            value = tmp*1000;
            if (value >1) {
                timeUnit = "ms";
            } else {
                value = tmp*1000000;
                if (value >= 1) {
                    timeUnit = "us";
                } else {
                    value = tmp*1000000000;
                    if (value >= 1) {
                        timeUnit = "ns";
                    }
                }
            }
        }
        return String.format("%.04f %s ", value, timeUnit);
    }

}

