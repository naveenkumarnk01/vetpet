package utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

/**
 * Created by Naveen on 06-Dec-15.
 */

public class VetPetPreferences {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "vetpetsharedmode";


    // User name (make variable public to access from outside)
    // public static final String KEY_NAME = "name";
    //
    // // Email address (make variable public to access from outside)
    // public static final String KEY_EMAIL = "email";

    // Constructor
    public VetPetPreferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Store string value into Sharedpreference
     */
    public void storeStringPrefereneValue(String keyname, String value) {
        // Storing name in pref
        editor.putString(keyname, value);

        // commit changes
        editor.commit();
    }

    /**
     * Store int value into Sharedpreference
     */
    public void storeIntPrefereneValue(String keyname, int value) {
        editor.putInt(keyname, value);
        // commit changes
        editor.commit();
    }

    /**
     * Store boolean value into Sharedpreference
     */
    public void storeBooleanPrefereneValue(String keyname, boolean value) {
        editor.putBoolean(keyname, value);
        // commit changes
        editor.commit();
    }


    /**
     * Get stored preference data
     */
    public HashMap<String, String> getUserDetails(String keyname) {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(keyname, pref.getString(keyname, null));

        // return user
        return user;
    }

    public int getIntStored(String keyname) {
        return pref.getInt(keyname, 0);
    }

    public boolean getBooleanStored(String keyname) {
        return pref.getBoolean(keyname, false);
    }

}