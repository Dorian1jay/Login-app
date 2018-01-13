package com.example.jay.loginapp.Helper;

import android.util.Patterns;

/**
 * Created by jay on 19/12/17.
 */

public class ValidateHelper {

    public boolean validatePassword(String P1,String P2){

        if(! P1.equals(P2))
            return false;

        return true;
    }
    public boolean checkEmpty(String email,String pass1,String pass2)
    {
        if(email.isEmpty() || pass1.isEmpty() ||pass2.isEmpty())
            return false;

        return true;

    }
    public boolean checkEmpty(String Name,String loc,String instrument,String bio,String styles)
    {
        if(Name.isEmpty() || loc.isEmpty() ||instrument.isEmpty() ||bio.isEmpty()||styles.isEmpty())
            return false;

        return true;

    }
    public boolean checkEmpty(String email,String pass1)
    {
        if(email.isEmpty() || pass1.isEmpty())
            return false;

        return true;

    }
    public boolean isValidEmail(CharSequence target) {

        return ( !(target.toString().contains("..")) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
