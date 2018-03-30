package com.cs386.NAUToDo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements RecyclerAdapter.Clickable{

    private RecyclerView recyclerView;
    private View containerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RecyclerAdapter adapter;
    String accountPK;
    String listPK;
    DataBaseHelper mDatabaseHelper;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDatabaseHelper = new DataBaseHelper(this.getContext());
        Intent i = new Intent();
        accountPK = i.getStringExtra("accountPK");
        listPK = i.getStringExtra("listPK");
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.nav_drawer_list);
        adapter = new RecyclerAdapter(getActivity(), getData());
        adapter.setClickable(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<ListItem> getData() {
        List<ListItem> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_list_black_24dp, R.drawable.ic_people_black_24dp, R.drawable.ic_store_black_24dp, R.drawable.ic_exit_to_app_black_24dp, R.drawable.ic_info_black_24dp};
        String[] titles = {"My Lists", "Friends", "Stores", "Log Out", "About"};
        for (int i = 0; i < titles.length && i < icons.length; i++) {
            ListItem current = new ListItem();
            current.title = titles[i];
            current.iconId = icons[i];
            data.add(current);
        }
        return data;
    }

    public void setUp(int fragmentID, DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentID);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    @Override
    public void itemClicked(View v, int position) {
       /* switch (position) {
            case 0: {
                Intent intent = new Intent(this.getContext().getApplicationContext(),MyListsActivity.class);
                intent.putExtra("accountPK", accountPK);
                intent.putExtra("listName",listPK);
                startActivity(intent);
                break;
            }
            case 1: {
                startActivity(new Intent(getActivity(), FriendsActivity.class));
                break;
            }
            case 2: {
                startActivity(new Intent(getActivity(), StoresActivity.class));
                break;
            }
            case 3: {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            }
            case 4: {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        }*/

    }

    /*public void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        Shared
    }*/
}
