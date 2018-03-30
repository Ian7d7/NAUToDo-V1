package com.cs386.NAUToDo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT_PK = "com.cs399.i_buy_3";
    private EditText username;
    private EditText password;
    private Button signIn;
    private TextView signUp;
    private Button ClearA;
    private Button ClearL;
    private Button ClearI;
    private Button Initialize;
    String accountPK;
    Bundle passingValues;
    private DataBaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialize = (Button) findViewById(R.id.Initialize);
        username = (EditText) findViewById(R.id.usernameField);
        password = (EditText) findViewById(R.id.passwordField);
        signIn = (Button) findViewById(R.id.signInButton);
        mDatabaseHelper = new DataBaseHelper(this);
        signUp = (TextView) findViewById(R.id.signUpTV);

        /*testing
        //ClearA = (Button) findViewById(R.id.ClearA);
        //ClearL = (Button) findViewById(R.id.ClearL);
        //ClearI = (Button) findViewById(R.id.ClearI);
        //Initialize = (Button) findViewById(R.id.Initialize);
        //Initialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.INIT();
            }
        });
        //ClearA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.CLEARACCOUNTS();
            }
        });
        ClearL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.CLEARLISTS();
            }
        });
        ClearI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.CLEARITEMS();
            }
        });*/
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });


    }

    public void signIn(View v)
    {
        accountPK = mDatabaseHelper.LOGIN(username.getText()+"", password.getText()+"");
        toastMessage(accountPK+"");

        if(accountPK!="-1")
        {
            //toastMessage(accountPK);
            Intent intent = new Intent(LoginActivity.this, MyListsActivity.class);
            intent.putExtra("accountPK", accountPK);
            startActivity(intent);
        }
        else
        {
           toastMessage("Incorrect username or password, please try again");
        }
    }

    private void toastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
