package lasa.urbanheatisland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataCollection extends AppCompatActivity {

    private int l = 0;

    DatabaseReference databaseTemperaturePoint = FirebaseDatabase.getInstance().getReference("datapoint");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        FirebaseApp.getInstance();
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


        if(temp.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),"Please Enter A Valid Temperature Value",Toast.LENGTH_LONG).show();

        }else{
            String id = databaseTemperaturePoint.push().getKey();
            List d = new ArrayList();
            d.add("entry" + l);
            l++;
            d.add(shadeType);
            d.add(Boolean.toString(isshade));
            d.add(groundType);
            d.add(Double.toString(temperatureInput));
            databaseTemperaturePoint.child(id).setValue(d);
        }


        Log.i("Send this" , shadeType + " "  + isshade + " " + groundType + " " + temperatureInput);
        

    }


}
