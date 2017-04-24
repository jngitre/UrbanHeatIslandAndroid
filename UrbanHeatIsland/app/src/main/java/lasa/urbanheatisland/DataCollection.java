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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataCollection extends AppCompatActivity {

    DatabaseReference databaseTemperaturePoint = FirebaseDatabase.getInstance().getReference("datapoint");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
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
            

            
        }

        String id = databaseTemperaturePoint.push().getKey();
        DataPoint d = new DataPoint(id , shadeType , isshade,  groundType, temperatureInput);
        databaseTemperaturePoint.child(id).setValue(d);


        Log.i("Send this:" , id + " " + shadeType + " "  + isshade + " " + groundType + " " + temperatureInput);
        

    }


}
