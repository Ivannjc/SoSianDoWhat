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

public class WetActivityGood extends AppCompatActivity {

    ListView lvWetGood;
    EditText etWetGood;
    Button btnAddWetGood;

    ArrayList<WetItemGood> alWetListGood = new ArrayList<WetItemGood>();
    ArrayAdapter aaWetGood;
    String mood = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wet_good);
        Intent preIntent = getIntent();
        mood = preIntent.getStringExtra("Mood");
        lvWetGood = (ListView)findViewById(R.id.listViewWetGood);
        etWetGood = (EditText)findViewById(R.id.editTextWetGood);
        btnAddWetGood = (Button)findViewById(R.id.buttonAddWetGood);
        float Progress = getIntent().getFloatExtra("decimalProgress",0);

        btnAddWetGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strWet = etWetGood.getText().toString();

                //database
                DBHelper db = new DBHelper(WetActivityGood.this);

                db.insertWet(strWet);
                db.close();

                DBHelper dbRetrieve = new DBHelper(WetActivityGood.this);
                alWetListGood = dbRetrieve.getWetGood();
                dbRetrieve.close();

                aaWetGood = new ArrayAdapter<WetItemGood>(WetActivityGood.this, android.R.layout.simple_list_item_1, alWetListGood);

                lvWetGood.setAdapter(aaWetGood);
                aaWetGood.notifyDataSetChanged();

            }
        });

        lvWetGood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Inflate the input.xml layout file
//                LayoutInflater inflater = (LayoutInflater).getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                AlertDialog.Builder b = new AlertDialog.Builder(WetActivityGood.this);
                b.setIcon(android.R.drawable.ic_dialog_alert);


                b.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        DBHelper db = new DBHelper(WetActivityGood.this);
                        db.deleteItemWetGood(alWetListGood.get(position).getWetIdGood());
                        db.close();
                        alWetListGood.remove(position);
                        Toast.makeText(WetActivityGood.this, "Deleted liao lol", Toast.LENGTH_SHORT).show();
                        aaWetGood.notifyDataSetChanged();

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
}
