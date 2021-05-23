package com.example.sio.android_medecin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import modele.Med;

public class DetailMedActivity extends AppCompatActivity {
    private Med m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_med);
        TextView label = (TextView) findViewById(R.id.label);
        Intent inter = getIntent();
        m = (Med) inter.getSerializableExtra("leMed");

        label.setText(m.getNom());
    }

    public void btnTel_click(View v) {
        Uri num = Uri.parse("tel:" + m.getTel());
        Intent i = new Intent(Intent.ACTION_CALL, num);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},
                1);
                startActivity(i);

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }
    public void btnSms_click(View v) {
        Intent i = new Intent(DetailMedActivity.this, SmsActivity.class);
                //Bundle myData = new Bundle();
                //myData.putString("leDep", lesDeps.get(position));
                //Med m = (Med) l.getAdapter().getItem(position);
                i.putExtra("leTel", m.getTel());
                startActivity(i);
    }
    public void btnMap_click(View v) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(m.getAdresse()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }

    }
      /*  Button btnTel = (Button) findViewById(R.id.btnTel);
        btnTel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Uri num = Uri.parse("tel:" + m.getTel());
                Intent i = new Intent(Intent.ACTION_CALL, num);

                startActivity(i);

            }
        });
        Button btnSms = (Button) findViewById(R.id.btnSms);
        btnSms.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DetailMedActivity.this, SmsActivity.class);
                //Bundle myData = new Bundle();
                //myData.putString("leDep", lesDeps.get(position));
                //Med m = (Med) l.getAdapter().getItem(position);
                i.putExtra("leTel", m.getTel());
                startActivity(i);

            }
        });
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(m.getAdresse()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }

//                final Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("geo:0,0?q=" + Uri.encode(m.getAdresse())));
//                startActivity(intent);

            }
        });
    }
    }*/
}
