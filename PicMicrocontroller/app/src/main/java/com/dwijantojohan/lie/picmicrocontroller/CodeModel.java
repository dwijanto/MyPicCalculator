package com.dwijantojohan.lie.picmicrocontroller;

/**
 * Created by dlie on 6/2/2016.
 */
public class CodeModel {
    private String codeName="";
    private String extension="";

    public CodeModel(String codeName,String extension){
        this.codeName=codeName;
        this.extension = extension.toLowerCase();
    }

    public String getCodeName(){
        return this.codeName;
    }
    public String getExtension(){
        return this.extension;
    }

}
