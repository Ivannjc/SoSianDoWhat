package sg.edu.rp.c346.sosiandowhat;

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
import android.widget.Toast;

import java.util.ArrayList;

public class WetActivity extends AppCompatActivity {
    ListView lvWet;
    EditText etWet;
    Button btnAddWet;

    ArrayList<WetItem> alWetList = new ArrayList<WetItem>();
    ArrayAdapter aaWet;
    String mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wet);
        Intent preIntent = getIntent();
        mood = preIntent.getStringExtra("Mood");
        lvWet = (ListView)findViewById(R.id.listViewWet);
        etWet = (EditText)findViewById(R.id.editTextWet);
        btnAddWet = (Button)findViewById(R.id.buttonAddWet);

        // alDryList.add("Badminton");
        // alDryList.add("Sleep");
        // alDryList.add("Cook");
        // aaDry = new ArrayAdapter<String>(DryActivity.this, android.R.layout.simple_list_item_1, alDryList);

        // lvDry.setAdapter(aaDry);
        // aaDry.notifyDataSetChanged();

        btnAddWet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strWet = etWet.getText().toString();

                //database
                DBHelper db = new DBHelper(WetActivity.this);

                db.insertWet(strWet);
                db.close();

                DBHelper dbRetrieve = new DBHelper(WetActivity.this);
                alWetList = dbRetrieve.getWet();
                dbRetrieve.close();

                aaWet = new ArrayAdapter(WetActivity.this, android.R.layout.simple_list_item_1, alWetList);

                lvWet.setAdapter(aaWet);
                aaWet.notifyDataSetChanged();

            }
        });

        lvWet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Inflate the input.xml layout file
//                LayoutInflater inflater = (LayoutInflater).getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                AlertDialog.Builder b = new AlertDialog.Builder(WetActivity.this);
                b.setIcon(android.R.drawable.ic_dialog_alert);


                b.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        DBHelper db = new DBHelper(WetActivity.this);
                        db.deleteItemWet(alWetList.get(position).getWetId());
                        db.close();
                        alWetList.remove(position);
                        Toast.makeText(WetActivity.this, "Deleted liao lol", Toast.LENGTH_SHORT).show();
                        aaWet.notifyDataSetChanged();

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


//        btnAddWet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String strWet = etWet.getText().toString();
//
//                //database
//                DBHelper db = new DBHelper(WetActivity.this);
//
//                db.insertWet(strWet);
//                db.close();
//
//                DBHelper dbRetrieve = new DBHelper(WetActivity.this);
//                alWetList = dbRetrieve.getWet();
//                dbRetrieve.close();
//
//                aaWet = new ArrayAdapter(WetActivity.this, android.R.layout.simple_list_item_1, alWetList);
//
//                lvWet.setAdapter(aaWet);
//                aaWet.notifyDataSetChanged();
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbRetrieve = new DBHelper(WetActivity.this);
        alWetList = dbRetrieve.getWet();
        dbRetrieve.close();

        if(mood.equals("Good")) {
//            alWetList.add("Drive to Mom's place");
//            alWetList.add("Netflix and chill");
//            alWetList.add("Cook");
            aaWet = new ArrayAdapter<WetItem>(WetActivity.this, android.R.layout.simple_list_item_1, alWetList);
        } else {
//            alWetList.add("Watch TV");
//            alWetList.add("Sleep");
//            alWetList.add("Cook");
            aaWet = new ArrayAdapter<WetItem>(WetActivity.this, android.R.layout.simple_list_item_1, alWetList);
        }

        lvWet.setAdapter(aaWet);
        aaWet.notifyDataSetChanged();
    }
}
