package com.example.geek.foodpingu;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class Breakfast extends Activity {

    ListView lv;
    Context context;
    private ProgressDialog pDialog;
    ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();

    // URL to get contacts JSON
    private static String url = "http://mcafee.0x10.info/api/app?type=json";
 
    // contacts JSONArray
    JSONArray contacts = null;
 
    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
    

    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
    public static String [] prgmNameList={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        new GetContacts().execute();
       
    }



    



private class GetContacts extends AsyncTask<Void, Void, Void> {
	 
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(Breakfast.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

        Log.d("Response: ", "> " + jsonStr);

        if (jsonStr != null) {
            try {
               // JSONObject jsonObj = new JSONObject(jsonStr);
                 
                // Getting JSON Array node
                contacts = new JSONArray(jsonStr);

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);
                     
                    String name = c.getString("name");
                    String imagee = c.getString("imagee");
                    String urldisplay = imagee;
               /*
                    Bitmap mIcon11 = null;
            	      try {
            	        InputStream in = new java.net.URL(urldisplay).openStream();
            	        mIcon11 = BitmapFactory.decodeStream(in);
            	      } catch (Exception e) {
            	          Log.e("Error", e.getMessage());
            	          e.printStackTrace();
            	      }
            	    bitmapArray.add(mIcon11);

            	   */
                    String type = c.getString("type");
                    String price = c.getString("price");
                    String rating = c.getString("rating");
                    String users = c.getString("users");
                    String last_update = c.getString("last_update");
                    String description = c.getString("description");
                    String url1 = c.getString("url");
                    // tmp hashmap for single contact
                    HashMap<String, String> contact = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    contact.put("name", name);
                    contact.put("imagee", imagee);
                    contact.put("type", type);
                    contact.put("price", price);
                    contact.put("rating", rating);
                    contact.put("users", users);
                    contact.put("last_update", last_update);
                    contact.put("description", description);
                    contact.put("url", url1);

                    // adding contact to contact list
                    contactList.add(contact);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
        
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter1(Breakfast.this, contactList)); // lv.setAdapter(new CustomAdapter1(Breakfast.this, contactList,bitmapArray));

    }

}

}