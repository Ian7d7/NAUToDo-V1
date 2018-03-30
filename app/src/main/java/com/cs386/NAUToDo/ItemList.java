package com.cs386.NAUToDo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity {
    String accountPK;
    String listPK;
    private Button MyLists;
    private Button AddItem;
    private TextView ItemName;
    private ListView mListView;
    DataBaseHelper mDatabaseHelper;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list2);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("My Lists");
        setSupportActionBar(toolbar);
        mDatabaseHelper = new DataBaseHelper(this);
        MyLists = (Button) findViewById(R.id.MyLists);
        AddItem = (Button) findViewById(R.id.AddItem);
        mListView = (ListView) findViewById(R.id.list);
        ItemName = (TextView) findViewById(R.id.ItemName);
        Intent receivedIntent = getIntent();
        accountPK = receivedIntent.getStringExtra("accountPK");
        listPK = receivedIntent.getStringExtra("listPK");

        populateListView();
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseHelper.ADDITEM(accountPK,listPK,ItemName.getText().toString());
                populateListView();
                ItemName.setText("");
            }
        });
        MyLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),MyListsActivity.class);
                intent.putExtra("accountPK", accountPK);
                intent.putExtra("listName",listPK);
                startActivity(intent);
            }
        });
    }
    private void populateListView() {
        Cursor data = mDatabaseHelper.ITEMLISTS(listPK+"");
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            listData.add(data.getString(2));
            //+", Quantity: "+data.getString(3)
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = parent.getItemAtPosition(position).toString();
                String itemPK = mDatabaseHelper.GETITEMPK(itemName);
                //toastMessage(itemName+"");
                Intent intent = new Intent(getApplicationContext(),EditItem.class);
                intent.putExtra("accountPK", accountPK);
                intent.putExtra("listPK",listPK);
                intent.putExtra("itemPK",itemPK);
                //intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbaractionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_about) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private void toastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
