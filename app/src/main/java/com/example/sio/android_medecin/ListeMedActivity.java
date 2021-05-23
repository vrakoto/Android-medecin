package com.example.sio.android_medecin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import modele.DAO;
import modele.Med;

public class ListeMedActivity extends ListActivity {


    private List<Med> lesMeds;
    String dep;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //on va chervher la vue
        setContentView(R.layout.activity_list_med);
        TextView label = (TextView) findViewById(R.id.label);
        Intent inter = getIntent();
        dep = inter.getStringExtra("leDep");
        label.setText(dep);
        //lesMeds = DAO.getLesMeds(dep);
        new LisMeds().execute();

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesMeds);
//        MedAdapter adapter = new MedAdapter(lesMeds, this);
//        setListAdapter(adapter);
        //Gestion du bouton de filtrage
 /*       Button btnFiltre = (Button) findViewById(R.id.btnCherche);
        final EditText choix = (EditText) findViewById(R.id.txtNom);
        btnFiltre.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                List<Med> newMeds = new ArrayList<Med>();
                for (Med unMed : lesMeds) {
                    if (unMed.getNom().startsWith(choix.getText().toString())) {
                        newMeds.add(unMed);
                    }
                }
                MedAdapter adapter = new MedAdapter(newMeds, getApplicationContext());
                setListAdapter(adapter);
            }
        });
  */
    }
    public void btnCherche_click(View v) {
        List<Med> newMeds = new ArrayList<Med>();
        EditText choix = (EditText) findViewById(R.id.txtNom);
                for (Med unMed : lesMeds) {
                    if (unMed.getNom().startsWith(choix.getText().toString())) {
                        newMeds.add(unMed);
                    }
                }
                MedAdapter adapter = new MedAdapter(newMeds, ListeMedActivity.this); //getApplicationContext());
                setListAdapter(adapter);

    }
  protected void onListItemClick(ListView l, View v, int position, long id) {

        //Toast.makeText(getApplicationContext(),
        //        "List is clicked" + lesDeps.get(position), Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, DetailMedActivity.class);
        //Bundle myData = new Bundle();
        //myData.putString("leDep", lesDeps.get(position));
        Med m = (Med) l.getAdapter().getItem(position);
        i.putExtra("leMed", m);
        startActivity(i);
        //super.onListItemClick(l, v, position, id);
    }

    /*
     * public boolean onKeyUp(int keyCode, KeyEvent event) { if(keyCode ==
     * KeyEvent.KEYCODE_SEARCH){ Toast.makeText(this, "touché", 1000).show(); }
     * return true;
     }
     */

    private class LisMeds extends AsyncTask {

        @Override
        protected Object doInBackground(Object... paramss) {
            lesMeds = DAO.getLesMeds(dep);
            return null;
        }

        protected void onPostExecute(Object o) {

            MedAdapter adapter = new MedAdapter(lesMeds, ListeMedActivity.this);
            setListAdapter(adapter);
        }
    }
}
