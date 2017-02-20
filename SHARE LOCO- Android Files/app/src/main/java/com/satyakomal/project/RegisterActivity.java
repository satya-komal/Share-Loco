package com.satyakomal.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class RegisterActivity extends AppCompatActivity {
    EditText email, password, phone_no;
    Button reg_button;
    AlertDialog.Builder builder;
    //private static final String FIREBASE_URL= BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.reg_email);
        password = (EditText) findViewById(R.id.reg_password);
        phone_no = (EditText) findViewById(R.id.reg_phone_no);
        reg_button = (Button) findViewById(R.id.reg_button);
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || password.getText().toString().equals("") || phone_no.getText().toString().equals("")) {
                    builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Something is wrong");
                    builder.setMessage("Please fill all the required details");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                }
                 else {
                    BackgroundTask backgroundTask = new BackgroundTask(RegisterActivity.this);
                    backgroundTask.execute("register", email.getText().toString(), password.getText().toString(), phone_no.getText().toString());

                }
            }
        });
    }
}