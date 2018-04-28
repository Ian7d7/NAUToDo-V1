package com.cs386.NAUToDo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    ArrayList<String> listData;
    ArrayAdapter<String> adapter;
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

        Cursor data = mDatabaseHelper.ITEMLISTS(listPK+"");
        listData = new ArrayList<>();
        while(data.moveToNext())
        {
            listData.add(data.getString(2));
            //+", Quantity: "+data.getString(3)
        }
        adapter = new ArrayAdapter<String>(ItemList.this,
                android.R.layout.simple_list_item_multiple_choice, listData);

        View.OnClickListener addListner = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listData.add(ItemName.getText().toString());
                ItemName.setText("");
                adapter.notifyDataSetChanged();
            }
        };


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                SparseBooleanArray positionchecker = mListView.getCheckedItemPositions();

                int count = mListView.getCount();

                for(int item = count - 1; item >= 0; item-- ){
                    if(positionchecker.get(item)){
                        adapter.remove(listData.get(item));
                    }
                }

                positionchecker.clear();

                adapter.notifyDataSetChanged();

                return false;
            }
        });

        AddItem.setOnClickListener(addListner);

        mListView.setAdapter(adapter);
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
