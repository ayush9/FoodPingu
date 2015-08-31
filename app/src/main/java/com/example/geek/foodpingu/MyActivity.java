package com.example.geek.foodpingu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MyActivity extends ActionBarActivity {


    android.app.FragmentManager manager = getFragmentManager();
    final android.app.FragmentTransaction transaction = manager.beginTransaction();

    foodmap fraga = new foodmap();
    int mPosition = -1;
    String mTitle = "";

    // Array of strings storing country names
    String[] mCountries ;

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] mFlags = new int[]{
            R.drawable.ic_communities,
            R.drawable.ic_home,
            R.drawable.ic_pages,
            R.drawable.ic_people,
            R.drawable.ic_photos,
            R.drawable.ic_whats_hot
    };

    // Array of strings to initial counts
    String[] mCount = new String[]{
            "", "", "", "", "",
            "", "", "", "", "" };

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawer ;
    private List<HashMap<String,String>> mList ;
    private SimpleAdapter mAdapter;
    final private String COUNTRY = "country";
    final private String FLAG = "flag";
    final private String COUNT = "count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Getting an array of country names
        mCountries = getResources().getStringArray(R.array.countries);

        // Title of the activity
        mTitle = (String)getTitle();

        // Getting a reference to the drawer listview
        mDrawerList = (ListView) findViewById(R.id.drawer_list);

        // Getting a reference to the sidebar drawer ( Title + ListView )
        mDrawer = ( LinearLayout) findViewById(R.id.drawer);

        // Each row in the list stores country name, count and flag
        mList = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<6;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put(COUNTRY, mCountries[i]);
            hm.put(COUNT, mCount[i]);
            hm.put(FLAG, Integer.toString(mFlags[i]) );
            mList.add(hm);
        }

// Keys used in Hashmap
        String[] from = { FLAG,COUNTRY};

// Ids of views in listview_layout
        int[] to = { R.id.flag , R.id.country };

// Instantiating an adapter to store each items
        // R.layout.drawer_layout defines the layout of each item
        mAdapter = new SimpleAdapter(this, mList, R.layout.fragment_my, from, to);

        // Getting reference to DrawerLayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        // Creating a ToggleButton for NavigationDrawer with drawer event listener
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer , R.string.drawer_open,R.string.drawer_close){

            /** Called when drawer is closed */
            public void onDrawerClosed(View view) {
                highlightSelectedCountry();
                supportInvalidateOptionsMenu();
            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("FoodPingu");
                supportInvalidateOptionsMenu();
            }
        };


        transaction.replace(R.id.content_frame, fraga);
        transaction.commit();

        // Setting event listener for the drawer
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        final Feedback frag=new Feedback();
        final Logout frag2=new Logout();
       // final Share fragb = new Share();

        // ItemClick event handler for the drawer items
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                // Increment hit count of the drawer list item
                //incrementHitCount(position);
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();



                if(position ==0) {


                    transaction.replace(R.id.content_frame, fraga);
                    transaction.commit();
                }else if(position ==1){
                    Toast.makeText(getApplicationContext(), mCountries[position], Toast.LENGTH_LONG).show();
                }
                else if(position ==2){
                    Intent i =new Intent(MyActivity.this,Hello.class);
                    startActivity(i);
                }
                else if(position ==3){
                //    Toast.makeText(getApplicationContext(), mCountries[position], Toast.LENGTH_LONG).show();
                    Intent i =new Intent(MyActivity.this,Share.class);
                    startActivity(i);
                  // transaction.replace(R.id.content_frame,fragb);
                  // transaction.commit();
                }
                else if(position ==4){



                    transaction.replace(R.id.content_frame, frag);
                    transaction.commit();
                }
                else if (position == 5) {



                    transaction.replace(R.id.content_frame, frag2);
                    transaction.commit();
                }

                mDrawerLayout.closeDrawer(mDrawer);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mDrawerList.setAdapter(mAdapter);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public void showFragment(int position){

        //Currently selected country
        mTitle = mCountries[position];

// Creating a fragment object
        CountryFragment cFragment = new CountryFragment();

// Creating a Bundle object
        Bundle data = new Bundle();

// Setting the index of the currently selected item of mDrawerList
        data.putInt("position", position);

// Setting the position to the fragment
        cFragment.setArguments(data);

// Getting reference to the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

// Creating a fragment transaction
        FragmentTransaction ft = fragmentManager.beginTransaction();

// Adding a fragment to the fragment transaction
        ft.replace(R.id.content_frame, cFragment);

// Committing the transaction
        ft.commit();
    }

    // Highlight the selected country : 0 to 4
    public void highlightSelectedCountry(){
        int selectedItem = mDrawerList.getCheckedItemPosition();

        if(selectedItem > 4)
            mDrawerList.setItemChecked(mPosition, true);
        else
            mPosition = selectedItem;

        if(mPosition!=-1)
            getSupportActionBar().setTitle(mCountries[mPosition]);
    }
}