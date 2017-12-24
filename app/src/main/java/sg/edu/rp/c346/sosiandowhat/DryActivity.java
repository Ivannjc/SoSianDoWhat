package sg.edu.rp.c346.sosiandowhat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class DryActivity extends AppCompatActivity {
    ListView lvDry;
    EditText etDry;
    Button btnAddDry;

    ArrayList<DryItem> alDryList = new ArrayList<DryItem>();
    ArrayAdapter aaDry;
    String mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry);
        Intent preIntent = getIntent();
        mood = preIntent.getStringExtra("Mood");
        lvDry = (ListView)findViewById(R.id.listViewDry);
        etDry = (EditText)findViewById(R.id.editTextDry);
        btnAddDry = (Button)findViewById(R.id.buttonAddDry);

       // alDryList.add("Badminton");
       // alDryList.add("Sleep");
       // alDryList.add("Cook");
       // aaDry = new ArrayAdapter<String>(DryActivity.this, android.R.layout.simple_list_item_1, alDryList);

       // lvDry.setAdapter(aaDry);
       // aaDry.notifyDataSetChanged();
        float Progress = getIntent().getFloatExtra("decimalProgress",0);


        btnAddDry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDry = etDry.getText().toString();

                //database
                DBHelper db = new DBHelper(DryActivity.this);

                db.insertDry(strDry);
                db.close();

                DBHelper dbRetrieve = new DBHelper(DryActivity.this);
                alDryList = dbRetrieve.getDry();
                dbRetrieve.close();

                aaDry = new ArrayAdapter<DryItem>(DryActivity.this, android.R.layout.simple_list_item_1, alDryList);

                lvDry.setAdapter(aaDry);
                aaDry.notifyDataSetChanged();

            }
        });

        lvDry.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Inflate the input.xml layout file
//                LayoutInflater inflater = (LayoutInflater).getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                AlertDialog.Builder b = new AlertDialog.Builder(DryActivity.this);
                b.setIcon(android.R.drawable.ic_dialog_alert);


                b.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        DBHelper db = new DBHelper(DryActivity.this);
                        db.deleteItem(alDryList.get(position).getDryId());
                        db.close();
                        alDryList.remove(position);
                        Toast.makeText(DryActivity.this, "Deleted liao lol", Toast.LENGTH_SHORT).show();
                        aaDry.notifyDataSetChanged();

                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                b.show();
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbRetrieve = new DBHelper(DryActivity.this);
        alDryList = dbRetrieve.getDry();
        dbRetrieve.close();


        if(mood.equals("Good")) {
//            alDryList.add("Badminton");
//            alDryList.add("Basketball");
//            alDryList.add("Soccer");
            aaDry = new ArrayAdapter<DryItem>(DryActivity.this, android.R.layout.simple_list_item_1, alDryList);
        } else {
  //          alDryList.add("Watch TV");
    //        alDryList.add("Sleep");
      //      alDryList.add("Cook");
            aaDry = new ArrayAdapter<DryItem>(DryActivity.this, android.R.layout.simple_list_item_1, alDryList);
        }


        lvDry.setAdapter(aaDry);
        aaDry.notifyDataSetChanged();
    }
}
