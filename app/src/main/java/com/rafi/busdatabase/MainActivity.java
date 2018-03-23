package com.rafi.busdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class MainActivity extends AppCompatActivity {

    EditText location,busName,type,hour,minute,amPm;
    SpinnerDialog busNameSp,typeSp,hourSp,minuteSp,amPmSp;
    Button update;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    ArrayList<String> busNameS = new ArrayList<>();
    ArrayList<String> typeS = new ArrayList<>();
    ArrayList<String> amPmS = new ArrayList<>();

    ArrayList<String>  hourI = new ArrayList<>();
    ArrayList<String> minuteI = new ArrayList<>();



    void load(){
        String[] busses = {"Taranga","Srabon","Choitaly","Moitri","Anondo","Basanta","Boishakhi","Falguni"};
        String[] types = {"up","down"};
        String[] amPms = {"AM","PM"};

        for (int i = 0; i <busses.length ; i++) {
            busNameS.add(busses[i]);
        }

        for (int i = 0; i < types.length; i++) {
            typeS.add(types[i]);
        }

        for (int i = 0; i < amPms.length; i++) {
            amPmS.add(amPms[i]);
        }

        for (int i = 1; i <=12 ; i++) {
            hourI.add(""+i);
        }

        for (int i = 0; i <60 ; i++) {
            minuteI.add(""+i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location = (EditText) findViewById(R.id.area);
        busName = (EditText) findViewById(R.id.bus);
        hour = (EditText) findViewById(R.id.hour);
        minute = (EditText) findViewById(R.id.minute);
        amPm = (EditText) findViewById(R.id.am_pm);
        type = (EditText) findViewById(R.id.updown);

        load();

        busNameSp = new SpinnerDialog(this,busNameS,"Select bus:");
        busNameSp.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                busName.setText(s);
            }
        });
        busName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busNameSp.showSpinerDialog();
            }
        });

        typeSp = new SpinnerDialog(this,typeS,"Select bus:");
        typeSp.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                type.setText(s);
            }
        });
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeSp.showSpinerDialog();
            }
        });


        hourSp = new SpinnerDialog(this,hourI,"Select bus:");
        hourSp.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                hour.setText(s);
            }
        });
        hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hourSp.showSpinerDialog();
            }
        });


        minuteSp = new SpinnerDialog(this,minuteI,"Select bus:");
        minuteSp.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                minute.setText(s);
            }
        });
        minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minuteSp.showSpinerDialog();
            }
        });


        amPmSp = new SpinnerDialog(this,amPmS,"Select bus:");
        amPmSp.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                amPm.setText(s);
            }
        });
        amPm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amPmSp.showSpinerDialog();
            }
        });


        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lc,bs,ud,hh,mm,ampm,out;
                lc = location.getText().toString().trim();
                bs = busName.getText().toString().trim();
                ud = type.getText().toString().trim();
                hh = hour.getText().toString().trim();
                mm = minute.getText().toString().trim();
                ampm = amPm.getText().toString().trim();
                out = bs+"-"+hh+"+"+mm+"+"+ampm;

                db.child(lc).child(ud).push().setValue(out);
                db.child("DU Campus").child(ud).push().setValue(out);
            }
        });
    }
}
