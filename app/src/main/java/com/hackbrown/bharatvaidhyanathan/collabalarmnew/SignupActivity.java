package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

/**
 * Created by akhilasankar on 2/5/17.
 */

        import android.app.ProgressDialog;
        import android.content.ContentValues;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.*;
        import java.net.URLConnection;
        import java.net.URLEncoder;

        import butterknife.ButterKnife;
        import butterknife.InjectView;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signup();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() throws IOException {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        BufferedReader reader=null;

        //boolean flag = true;
        try {
            URL url = new URL("http://138.16.49.170:8080/signup/");
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name);
            params.put("email", email);
            params.put("password", password);
            String response = HttpRequest.post(url).form(params).body();

//        URL url = new URL("http://138.16.49.170:8080/signup/");
//            String data = URLEncoder.encode("name", "UTF-8")
//                    + "=" + URLEncoder.encode(name, "UTF-8");
//            data +="&" +  URLEncoder.encode("username", "UTF-8")
//                    + "=" + URLEncoder.encode(email, "UTF-8");
//            data += "&" + URLEncoder.encode("password", "UTF-8")
//                    + "=" + URLEncoder.encode(password, "UTF-8");
//            System.out.println(data);
//            URLConnection urlConnection = url.openConnection();
//            urlConnection.setReadTimeout(10000);
//            urlConnection.setConnectTimeout(15000);
//            //urlConnection.setRequestMethod("POST");
//            urlConnection.setDoOutput(true);
//            OutputStream os = urlConnection.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//            writer.write(data);
//            writer.flush();
//            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while((line = reader.readLine()) != null)
//            {
//                // Append server response in string
//                sb.append(line + "\n");
//            }
            System.out.println(response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


}
