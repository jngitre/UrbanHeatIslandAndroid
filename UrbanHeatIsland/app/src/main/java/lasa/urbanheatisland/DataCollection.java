package lasa.urbanheatisland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DataCollection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
    }

    // Data retrieval into the database
    public void submitClick(View v) {

        EditText temp = (EditText) findViewById(R.id.Temperature);

        if(temp.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),"Please Enter A Valid Temperature Value",Toast.LENGTH_LONG).show();

        }


        double temperatureInput = Double.parseDouble(temp.getText().toString());











    }


}
