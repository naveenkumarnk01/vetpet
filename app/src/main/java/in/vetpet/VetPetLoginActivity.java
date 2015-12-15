package in.vetpet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utilities.DialogUtil;
import utilities.HttpUtil;
import utilities.PhoneNumberUtil;


/**
 * A login screen that offers login via email/password.
 */
public class VetPetLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private EditText ed, ed2;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_pet_login);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        ed = (EditText) findViewById(R.id.txt_ph_no);
        ed2 = (EditText) findViewById(R.id.txt_password);
        ed.setText("9886216874");
        ed2.setText("clarence");
        activity = this;

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                // Initialize login asynctask
                LoginAsyncTask obj = new LoginAsyncTask();
                //TODO: validation of input
                String msisdn = ed.getText().toString();
                String password = ed2.getText().toString();
                Boolean isValiduser = PhoneNumberUtil.isValidPhoneNumber(msisdn);
                if (isValiduser == null && !isValiduser) {
                    ed.setError("phone number is incorrect");
                } else {
                    obj.execute(msisdn, password);
                }
                // pass username password to asynctask

                break;
            case R.id.btn_signup:
                //TODO:
                break;
        }
    }

    public class LoginAsyncTask extends AsyncTask<String, Void, String> {
        private ProgressDialog dialog;

        /**
         * application context.
         */

        public LoginAsyncTask() {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            String login_str = HttpUtil.callLogin(params[0], params[1]);
            return login_str;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
//            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            //TODO: validate for results
            if ("SUCCESS".equalsIgnoreCase(result.replace("\"","").trim())) {
                Intent it = new Intent(VetPetLoginActivity.this, VetPetHomeActivity.class);
                startActivity(it);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                DialogUtil.showCustomAlertDialog(activity,result,0,getLayoutInflater(),activity);
            }
        }
    }
}