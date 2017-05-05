package lasa.urbanheatisland;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataCollection extends AppCompatActivity {

    private int l = 0;
    DatabaseReference databaseTemperaturePoint = FirebaseDatabase.getInstance().getReference("datapoint");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        FirebaseApp.getInstance();

        CheckBox isShade = (CheckBox) findViewById(R.id.shade);
        isShade.setOnClickListener(new View.OnClickListener() {
            Spinner shade = (Spinner) findViewById(R.id.shadeType);
            TextView shadeTitle = (TextView) findViewById(R.id.shadeSrc);

            @Override
            public void onClick(View v) {
                if (shade.getVisibility() == View.INVISIBLE) {
                    shade.setVisibility(View.VISIBLE);
                    shadeTitle.setVisibility(View.VISIBLE);
                } else {
                    shade.setVisibility(View.INVISIBLE);
                    shadeTitle.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    // Data retrieval into the database
    public void submitClick(View v) {
        Spinner ground = (Spinner) findViewById(R.id.groundType);
        EditText temp = (EditText) findViewById(R.id.Temperature);
        Spinner shade = (Spinner) findViewById(R.id.shadeType);
        CheckBox isShade = (CheckBox) findViewById(R.id.shade);
        String shadeType = shade.getSelectedItem().toString();
        Boolean isshade = isShade.isChecked();
        String groundType = ground.getSelectedItem().toString();
        double temperatureInput = Double.parseDouble(temp.getText().toString());
        String time = DateFormat.getDateTimeInstance().format(new Date());

        if (temp.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Please Enter A Valid Temperature Value", Toast.LENGTH_LONG).show();

        } else {
            String strisshade;
            if (!isshade) {
                shadeType = "None";
                strisshade = "No";
            } else {
                strisshade = "Yes";
            }

            String id = databaseTemperaturePoint.push().getKey();
            List d = new ArrayList();
            d.add("entry" + l);
            l++;
            d.add(Double.toString(temperatureInput));
            d.add(groundType);
            d.add(strisshade);
            d.add(shadeType);
            d.add(time);
            databaseTemperaturePoint.child(id).setValue(d);

            temp.setText("");
            Toast.makeText(getApplicationContext(), "Thank you for contributing!", Toast.LENGTH_SHORT).show();
        }


    }

}
