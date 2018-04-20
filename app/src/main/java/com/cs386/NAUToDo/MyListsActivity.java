package com.cs386.NAUToDo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

public class MyListsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String accountPK;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar toolbar;
    private ListView mListView;
    private Button AddList;
    private Button LogOut;
    private TextView ListName;
    DataBaseHelper mDatabaseHelper;
    private String[] fillList = {"Apples", "Bananas", "Corn", "Dog", "Elephant", "Fish"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("My Lists");
        setSupportActionBar(toolbar);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_frag);
        drawerFragment.setUp(R.id.nav_drawer_frag, (DrawerLayout)findViewById(R.id.drawer_layout), toolbar);

        ListView list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);


        AddList = (Button) findViewById(R.id.AddList);
        ListName = (TextView) findViewById(R.id.ListName);
        mDatabaseHelper = new DataBaseHelper(this);
        mListView = (ListView) findViewById(R.id.list);
        Intent receivedIntent = getIntent();
        accountPK = receivedIntent.getStringExtra("accountPK");

        populateListView();

        AddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.ADDLIST(accountPK,ListName.getText().toString());

                populateListView();

            }
        });


    }
    private void populateListView() {
        Cursor data = mDatabaseHelper.ACCOUNTLISTS(accountPK+"");//accountPK.toString()+"" doesnt work for some reason
        //Cursor data = mDatabaseHelper.ACCOUNTLISTS(accountPK.toString()+"");
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            listData.add(data.getString(0));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String listName = parent.getItemAtPosition(position).toString();
                String listPK = mDatabaseHelper.GETLISTPK(accountPK,listName);

                Intent intent = new Intent(getApplicationContext(),ItemList.class);
                intent.putExtra("accountPK", accountPK);
                intent.putExtra("listPK",listPK);
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


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }
    private void toastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
