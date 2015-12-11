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

import utilities.HttpUtil;


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
        activity = this;

    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_login :
                // Initialize login asynctask
                LoginAsyncTask obj = new LoginAsyncTask();
                //TODO: validation of input

                // pass username password to asynctask
                obj.execute(ed.getText().toString(), ed2.getText().toString());
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            //TODO: validate for results
            Intent it = new Intent(VetPetLoginActivity.this, VetPetHomeActivity.class);

            startActivity(it);
        }
    }
}
