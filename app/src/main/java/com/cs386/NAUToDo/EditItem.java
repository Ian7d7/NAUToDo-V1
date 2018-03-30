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
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditItem extends AppCompatActivity {
    String accountPK;
    String listPK;
    String itemPK;
    private TextView Quantity;
    private TextView Item;
    private Button Increment;
    private Button DeIncrement;
    private Button Rename;
    private Button Remove;
    private Button Back;
    DataBaseHelper mDatabaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Item = (TextView) findViewById(R.id.Item);
        Increment = (Button) findViewById(R.id.Increment);
        DeIncrement = (Button) findViewById(R.id.Dencrement);
        Remove = (Button) findViewById(R.id.Remove);
        Rename = (Button) findViewById(R.id.Rename);
        Back = (Button) findViewById(R.id.Back);
        Quantity = (TextView) findViewById(R.id.Quantity);
        mDatabaseHelper = new DataBaseHelper(this);
        Intent receivedIntent = getIntent();
        accountPK = receivedIntent.getStringExtra("accountPK");
        listPK = receivedIntent.getStringExtra("listPK");
        itemPK = receivedIntent.getStringExtra("itemPK");
        toastMessage(itemPK+"");


        Item.setText(mDatabaseHelper.GETITEMNAME(itemPK)+"");
        Quantity.setText(mDatabaseHelper.GETITEMQUANTITY(itemPK)+"");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
        Increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.INCITEMQUANTITY(itemPK);
                Quantity.setText(mDatabaseHelper.GETITEMQUANTITY(itemPK)+"");
            }
        });
        DeIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.DECITEMQUANTITY(itemPK);
                Quantity.setText(mDatabaseHelper.GETITEMQUANTITY(itemPK)+"");
            }
        });
        Rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.RENAMEITEM(itemPK,Item.getText().toString());
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ItemList.class);
                intent.putExtra("accountPK", accountPK);
                intent.putExtra("listPK",listPK);
                intent.putExtra("itemPK",itemPK);
                startActivity(intent);
            }
        });
    }
    private void toastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
