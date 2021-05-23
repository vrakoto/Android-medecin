package com.example.sio.android_medecin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {
private String leTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        TextView label = (TextView) findViewById(R.id.label);
        Intent inter = getIntent();
        leTel = inter.getStringExtra("leTel");
        label.setText(leTel);
    }
    public void btnEnvoyer_click(View v) {
        EditText message = (EditText) findViewById(R.id.txtMessage);
                String msg = message.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(leTel, null, msg, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
     /*   Button btnEnvoyer = (Button) findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EditText message = (EditText) findViewById(R.id.txtMessage);
                String msg = message.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(leTel, null, msg, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();


            }
        });
        */
    }
}
