package com.example.sio.android_medecin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import modele.Med;

public class MedAdapter extends BaseAdapter {
    private List<Med> lesMeds;
    private Context c;

    MedAdapter(List data, Context c) {
        lesMeds = data;
        this.c = c;
    }

    public int getCount() {
        return lesMeds.size();
    }

    public Object getItem(int i) {
        return lesMeds.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View v, ViewGroup vg) {

        //création de la vue en analysant le xml lignemed
        LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.lignemed, null);


        TextView nom = (TextView) v.findViewById(R.id.nom);
        TextView prenom = (TextView) v.findViewById(R.id.prenom);
        TextView tel = (TextView) v.findViewById(R.id.tel);
        Med leMed = lesMeds.get(i);
        nom.setText(leMed.getNom());
        prenom.setText(leMed.getPrenom());
        tel.setText(leMed.getTel());
        return v;
    }
}
