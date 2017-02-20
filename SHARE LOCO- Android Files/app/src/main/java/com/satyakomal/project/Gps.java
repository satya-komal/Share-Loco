package com.satyakomal.project;

import android.*;
import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
//import android.provider.Settings;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.provider.Settings.Secure;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

import static java.security.AccessController.getContext;

public class Gps extends AppCompatActivity {
    public String latVal,longVal;
    private Button cButton;
    private TextView latView;
    private TextView longView;

    private TextView device_id;
    private TextView user;
    int count =1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        //configureButton();
        Firebase.setAndroidContext(this);
         final Firebase ref = new Firebase("https://deviceinfo-afc2a.firebaseio.com/");

        cButton = (Button) findViewById(R.id.cButton);
        latView = (TextView) findViewById(R.id.latView);
        longView = (TextView) findViewById(R.id.longView);
        //device_id = (TextView) findViewById(R.id.device);
       // user = (TextView) findViewById(R.id.user);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                latVal = String.valueOf(location.getLatitude());
                latView.setText(latVal);
                longVal = String.valueOf(location.getLongitude());
                longView.setText(longVal);

// to store lat and long values in database
                String userInfo =LoginActivity.check;
                final String dev_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
               // device_id.setText(dev_id);
               // user.setText(userInfo);

                String update;
                ref.child(userInfo).child("DeivceID").setValue(dev_id);
                //ref.child(userInfo).child("User").setValue(count);
                //count++;
                ref.child(userInfo).child("Latitude").setValue(latVal);
                ref.child(userInfo).child("Longitude").setValue(longVal);

                /*Firebase messageRef = ref.child(userInfo);
                messageRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String,String> map = dataSnapshot.getValue(Map.class);
                        String device = map.get(dev_id);
                        String lati = map.get(latVal);
                        String longi = map.get(longVal);
                        Log.v("OUTPUT",device + " "+lati+" "+longi );
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                }*/

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        configureButton();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:

                configureButton();
                break;
            default:
                break;
        }
    }

    private void configureButton() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TESTING PURPOSE
                /*final Firebase fb = new Firebase("https://deviceinfo-afc2a.firebaseio.com/");
                String userInfo =LoginActivity.check;
                String dev_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
                fb.child(userInfo).child("DeviceID").setValue(dev_id);
                fb.child(userInfo).child("latitude").setValue("42.44");
                fb.child(userInfo).child("longitude").setValue("72.44");
                fb.child(userInfo).child("status").setValue("1");*/

                //ACTUAL CODE
                locationManager.requestLocationUpdates("gps",0,0, locationListener);
            }
        });
    }

}
