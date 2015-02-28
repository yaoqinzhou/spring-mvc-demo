package com.example.config;

import com.example.util.EncryptionDecryption;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    @Override
    protected String convertProperty(String propertyName,String propertyValue){
        if(isEncryptPropertyVal(propertyName)){
            try {
                EncryptionDecryption des = new EncryptionDecryption("tourhb");

                return des.decrypt(propertyValue);
            } catch (Exception e) {
                e.printStackTrace();
                return propertyValue;
            }
        }else{
            return propertyValue;
        }
    }

    private boolean isEncryptPropertyVal(String propertyName){
        if(propertyName.startsWith("encrypt")){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String [] args){
        EncryptionDecryption des;
        try {
            des = new EncryptionDecryption("tourhb");

            String decodeStr = des.decrypt("8b897923ad38fb4a8273c8cdc0d447b8");

            System.out.println("decodeStr = " + decodeStr);

            String enStr = des.encrypt("manager");

            System.out.println("enStr = " + enStr);

            System.out.println("destr = " + des.decrypt(enStr));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
