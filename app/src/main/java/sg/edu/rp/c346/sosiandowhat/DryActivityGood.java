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

public class DryActivityGood extends AppCompatActivity {

    ListView lvDryGood;
    EditText etDryGood;
    Button btnAddDryGood;

    ArrayList<DryItemGood> alDryListGood = new ArrayList<DryItemGood>();
    ArrayAdapter aaDryGood;
    String mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_good);
        Intent preIntent = getIntent();
        mood = preIntent.getStringExtra("Mood");
        lvDryGood = (ListView)findViewById(R.id.listViewDryGood);
        etDryGood = (EditText)findViewById(R.id.editTextDryGood);
        btnAddDryGood = (Button)findViewById(R.id.buttonAddDryGood);
        float Progress = getIntent().getFloatExtra("decimalProgress",0);


        btnAddDryGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDry = etDryGood.getText().toString();

                //database
                DBHelper db = new DBHelper(DryActivityGood.this);
                db.insertDryGood(strDry);
                db.close();

                DBHelper dbRetrieve = new DBHelper(DryActivityGood.this);
                alDryListGood = dbRetrieve.getDryGood();
                dbRetrieve.close();

                aaDryGood = new ArrayAdapter<DryItemGood>(DryActivityGood.this, android.R.layout.simple_list_item_1, alDryListGood);

                lvDryGood.setAdapter(aaDryGood);
                aaDryGood.notifyDataSetChanged();

            }
        });

        lvDryGood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Inflate the input.xml layout file
//                LayoutInflater inflater = (LayoutInflater).getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                AlertDialog.Builder b = new AlertDialog.Builder(DryActivityGood.this);
                b.setIcon(android.R.drawable.ic_dialog_alert);


                b.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        DBHelper db = new DBHelper(DryActivityGood.this);
                        db.deleteItemDryGood(alDryListGood.get(position).getDryIdGood());
                        db.close();
                        alDryListGood.remove(position);
                        Toast.makeText(DryActivityGood.this, "Deleted liao lol", Toast.LENGTH_SHORT).show();
                        aaDryGood.notifyDataSetChanged();

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
