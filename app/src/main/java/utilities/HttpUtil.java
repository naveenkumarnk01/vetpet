package utilities;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pradeen on 05/12/15.
 */
public class HttpUtil {
    public static String callLogin(String username, String password) {
        String jsonStr = null;
        System.out.println("Hello");
        try {
            Uri baseuri = Uri.parse("http://ec2-52-91-228-49.compute-1.amazonaws.com:8080/vetpetrest/rest/login").buildUpon()
                    .appendQueryParameter("username", username)
                    .appendQueryParameter("password", password)
                    .build();
            URL url = new URL(baseuri.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            InputStream in = httpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            jsonStr = buffer.toString();
            System.out.println(jsonStr);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

}
