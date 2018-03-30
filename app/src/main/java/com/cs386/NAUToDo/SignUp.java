package com.cs386.NAUToDo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {
    private Button CreateAccount;
    private TextView UserName;
    private TextView Password;
    private TextView Email;
    DataBaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        CreateAccount = (Button) findViewById(R.id.CreateAccount);
        UserName = (TextView) findViewById(R.id.AccountName);
        Password = (TextView) findViewById(R.id.Password);
        Email = (TextView) findViewById(R.id.Email);
        mDatabaseHelper = new DataBaseHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.ADDACCOUNT(UserName.getText().toString(),Password.getText().toString(),Email.getText().toString());
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
