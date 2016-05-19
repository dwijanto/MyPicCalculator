package com.dwijantojohan.lie.picmicrocontroller;

import java.io.IOError;
import java.io.IOException;

/**
 * Created by dlie on 5/13/2016.
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
}

