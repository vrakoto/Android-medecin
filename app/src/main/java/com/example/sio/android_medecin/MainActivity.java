package com.example.sio.android_medecin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import modele.DAO;

public class MainActivity extends ListActivity {
    /* */

    private List<String> lesDeps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on va chervher la vue
        setContentView(R.layout.activity_main);
        new LisDeps().execute();
        //lecture des deps
//        lesDeps = DAO.getLesDeps();
        //List<Med> lesMeds = DAO.getLesMeds("75");
        //Création de l'adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesDeps);
//
//        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //Toast.makeText(getApplicationContext(),
        //        "List is clicked" + lesDeps.get(position), Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, ListeMedActivity.class);
        //Bundle myData = new Bundle();
        //myData.putString("leDep", lesDeps.get(position));
        i.putExtra("leDep", lesDeps.get(position));
        startActivity(i);
        //super.onListItemClick(l, v, position, id);
    }



    private class LisDeps extends AsyncTask {

        @Override
        protected Object doInBackground(Object... paramss) {
            lesDeps = DAO.getLesDeps();
            return null;
        }

        protected void onPostExecute(Object o) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, lesDeps);
            setListAdapter(adapter);

        }

    }
}
