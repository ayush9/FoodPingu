package com.example.geek.foodpingu;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class foodmap extends Fragment {
    static final LatLng iiitm = new LatLng(26.250438,78.173469);
    static final LatLng OAT = new LatLng(26.246388, 78.172046);
    static final LatLng BH1 = new LatLng(26.249986, 78.169538);
    static final LatLng BH2 = new LatLng(26.250669, 78.169199);
    static final LatLng BH3 = new LatLng(26.249408, 78.169873);
    static final LatLng ACEDMIC = new LatLng(26.249793, 78.173035);
    static final LatLng GH = new LatLng(26.246978, 78.176079);
    static final LatLng MDP = new LatLng(26.249027, 78.177083);
    static final LatLng BC = new LatLng(26.252237, 78.168126);
    static final LatLng CANTEEN = new LatLng(26.249056, 78.174290);
    static final LatLng VH = new LatLng(26.246262, 78.173863);
    static final LatLng MG = new LatLng(26.250041,78.176394);
    LatLng p1 = null;
    LatLng latLng;EditText e;
    private GoogleMap googleMap;
    MarkerOptions markerOptions;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view=inflater.inflate(R.layout.activity_foodmap, container,false);


        Button b3=(Button)view.findViewById(R.id.button4);
        b3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Breakfast.class);
                startActivity(i);
            }
        });

        Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                e=(EditText)view.findViewById(R.id.editText1);
                String strAddress=e.getText().toString();
                if(strAddress!=null && !strAddress.equals("")){
                    new GeocoderTask().execute(strAddress);
                }
    	             	 /*
    	             	   Geocoder coder = new Geocoder(getActivity());

    	                  List<Address> address;


    	                  try {
    	                      address = coder.getFromLocationName(strAddress, 5);
    	                      if (address == null) {
    	                          //return null;
    	                      }
    	                      Address location = address.get(0);
    	                      location.getLatitude();
    	                      location.getLongitude();

    	                      p1 = new LatLng(location.getLatitude(), location.getLongitude() );

    	                  } catch (Exception ex) {

    	                      ex.printStackTrace();
    	                  }
    	         e.setText(p1.toString());

    	   */          }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        try {

            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String aadd(LatLng la) throws IOException
    {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        addresses = geocoder.getFromLocation(la.latitude, la.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
   /*     String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
    */    return address;
    }



    public LatLng find(View v)
    {
        e=(EditText)view.findViewById(R.id.editText1);
        String strAddress=e.getText().toString();
        Geocoder coder = new Geocoder(getActivity());
        List<Address> address;


        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }
        e.setText(p1.toString());
        return p1;



    }



    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            googleMap.addMarker(new MarkerOptions().
                    position(iiitm).title("ABV-IIITM"));
            googleMap.addMarker(new MarkerOptions().position(OAT).title("OPEN-AIR-THEATER"));
            googleMap.addMarker(new MarkerOptions().position(BH1).title("BOYS HOSTEL-1"));
            googleMap.addMarker(new MarkerOptions().position(BH2).title("BOYS HOSTEL-2"));
            googleMap.addMarker(new MarkerOptions().position(BH3).title("BOYS HOSTEL-3"));
            googleMap.addMarker(new MarkerOptions().position(ACEDMIC).title("ACEDMIC BLOCK"));
            googleMap.addMarker(new MarkerOptions().position(GH).title("GIRLS HOSTEL"));
            googleMap.addMarker(new MarkerOptions().position(MDP).title("MANAGEMENT BLOCK"));
            googleMap.addMarker(new MarkerOptions().position(BC).title("BUTTERFLY CONSERVATRY"));
            googleMap.addMarker(new MarkerOptions().position(CANTEEN).title("CANTEEN"));
            googleMap.addMarker(new MarkerOptions().position(VH).title("GUEST HOUSE"));
            googleMap.addMarker(new MarkerOptions().position(MG).title("MAIN GATE"));
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            //  googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(iiitm).zoom(15).build();

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                @Override
                public void onMapClick(LatLng latLng) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    // the title for the marker.
                    String sss="no"; // This will be displayed on taping the marker
                    try {
                        sss=aadd(latLng);
                        //      	e.setText("fdh");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //  markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                    markerOptions.title(sss);
                    // Clears the previously touched position
                    googleMap.clear();

                    // Animating to the touched position
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                    // Placing a marker on the touched position
                    googleMap.addMarker(markerOptions);
                }
            });

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>>{

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getActivity());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if(addresses==null || addresses.size()==0){
                Toast.makeText(getActivity(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            googleMap.clear();

            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latLng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(addressText);

                googleMap.addMarker(markerOptions);

                // Locate the first location
                if(i==0)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));
        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
        super.onDestroyView();
    }
    @Override
    public void onPause(){
        super.onPause();
        if(googleMap != null) {
            googleMap = null;
            {
            }
        }
    }
}

