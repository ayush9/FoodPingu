package com.example.geek.foodpingu;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class login extends Fragment {
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

    private GoogleMap googleMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_foodmap, container, false);
        return rootView;
    }



    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        //setContentView(R.layout.activity_foodmap);
        try {

            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

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


            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }



}