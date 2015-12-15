package utilities;

/**
 * Created by pradeen on 14/12/15.
 */
public class PhoneNumberUtil {
    public final static boolean isValidPhoneNumber(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            if (target.length() != 10) {
                return false;
            } else {
                return android.util.Patterns.PHONE.matcher(target).matches();
            }
        }
    }
}
