package com.satyakomal.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class LoginActivity extends AppCompatActivity {
    public static String check;
    TextView sign_up_text;
    EditText email, password;
    Button btnLogin;
    AlertDialog.Builder builder;
    //Firebase fb;
   // private static final String FIREBASE_URL= BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        sign_up_text= (TextView)findViewById(R.id.sign_up_text);
        sign_up_text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
    });

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.login_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( email.getText().toString().equals("") || password.getText().toString().equals("") ){
                    builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Fix this error");
                    builder.setMessage("Enter email and password");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else{
                    check =email.getText().toString();
                    BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this);
                    backgroundTask.execute("login", email.getText().toString(), password.getText().toString());
                }
            }
        });

    }
}
