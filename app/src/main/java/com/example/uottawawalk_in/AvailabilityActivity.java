package com.example.uottawawalk_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AvailabilityActivity extends AppCompatActivity {

    private TextView mon1,mon2,mon3,mon4,mon5,mon6,tue1,tue2,tue3,tue4,tue5,tue6,wed1,wed2,wed3,wed4,wed5,wed6,thu1,thu2,thu3,thu4,thu5,thu6,fri1,fri2,fri3,fri4,fri5,fri6;
    private Button btnHome;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Availability a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        mon1 = findViewById(R.id.mon1);
        mon2 = findViewById(R.id.mon2);
        mon3 = findViewById(R.id.mon3);
        mon4 = findViewById(R.id.mon4);
        mon5 = findViewById(R.id.mon5);
        mon6 = findViewById(R.id.mon6);
        tue1 = findViewById(R.id.tue1);
        tue2 = findViewById(R.id.tue2);
        tue3 = findViewById(R.id.tue3);
        tue4 = findViewById(R.id.tue4);
        tue5 = findViewById(R.id.tue5);
        tue6 = findViewById(R.id.tue6);
        wed1 = findViewById(R.id.wed1);
        wed2 = findViewById(R.id.wed2);
        wed3 = findViewById(R.id.wed3);
        wed4 = findViewById(R.id.wed4);
        wed5 = findViewById(R.id.wed5);
        wed6 = findViewById(R.id.wed6);
        thu1 = findViewById(R.id.thu1);
        thu2 = findViewById(R.id.thu2);
        thu3 = findViewById(R.id.thu3);
        thu4 = findViewById(R.id.thu4);
        thu5 = findViewById(R.id.thu5);
        thu6 = findViewById(R.id.thu6);
        fri1 = findViewById(R.id.fri1);
        fri2 = findViewById(R.id.fri2);
        fri3 = findViewById(R.id.fri3);
        fri4 = findViewById(R.id.fri4);
        fri5 = findViewById(R.id.fri5);
        fri6 = findViewById(R.id.fri6);
        btnHome = findViewById(R.id.btnBackHome);
        databaseReference = FirebaseDatabase.getInstance().getReference("Availability");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Query query = databaseReference.child(mUser.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                a = dataSnapshot.getValue(Availability.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(a.getMon1()){ mon1.setText("Available"); }
                if(a.getMon2()){ mon2.setText("Available"); }
                if(a.getMon3()){ mon3.setText("Available"); }
                if(a.getMon4()){ mon4.setText("Available"); }
                if(a.getMon5()){ mon5.setText("Available"); }
                if(a.getMon6()){ mon6.setText("Available"); }

                if(a.getTue1()){ tue1.setText("Available"); }
                if(a.getTue2()){ tue2.setText("Available"); }
                if(a.getTue3()){ tue3.setText("Available"); }
                if(a.getTue4()){ tue4.setText("Available"); }
                if(a.getTue5()){ tue5.setText("Available"); }
                if(a.getTue6()){ tue6.setText("Available"); }

                if(a.getWed1()){ wed1.setText("Available"); }
                if(a.getWed2()){ wed2.setText("Available"); }
                if(a.getWed3()){ wed3.setText("Available"); }
                if(a.getWed4()){ wed4.setText("Available"); }
                if(a.getWed5()){ wed5.setText("Available"); }
                if(a.getWed6()){ wed6.setText("Available"); }

                if(a.getThu1()){ thu1.setText("Available"); }
                if(a.getThu2()){ thu2.setText("Available"); }
                if(a.getThu3()){ thu3.setText("Available"); }
                if(a.getThu4()){ thu4.setText("Available"); }
                if(a.getThu5()){ thu5.setText("Available"); }
                if(a.getThu6()){ thu6.setText("Available"); }

                if(a.getFri1()){ fri1.setText("Available"); }
                if(a.getFri2()){ fri2.setText("Available"); }
                if(a.getFri3()){ fri3.setText("Available"); }
                if(a.getFri4()){ fri4.setText("Available"); }
                if(a.getFri5()){ fri5.setText("Available"); }
                if(a.getFri6()){ fri6.setText("Available"); }
            }
        };
        handler.postDelayed(r,500);

        mon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleMon1();
                if(a.getMon1()){
                    mon1.setText("Available");
                }
                else{
                    mon1.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        mon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleMon2();
                if(a.getMon2()){
                    mon2.setText("Available");
                }
                else{
                    mon2.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        mon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleMon3();
                if(a.getMon3()){
                    mon3.setText("Available");
                }
                else{
                    mon3.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        mon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleMon4();
                if(a.getMon4()){
                    mon4.setText("Available");
                }
                else{
                    mon4.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        mon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleMon5();
                if(a.getMon5()){
                    mon5.setText("Available");
                }
                else{
                    mon5.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        mon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleMon6();
                if(a.getMon6()){
                    mon6.setText("Available");
                }
                else{
                    mon6.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        tue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleTue1();
                if(a.getTue1()){
                    tue1.setText("Available");
                }
                else{
                    tue1.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        tue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleTue2();
                if(a.getTue2()){
                    tue2.setText("Available");
                }
                else{
                    tue2.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        tue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleTue3();
                if(a.getTue3()){
                    tue3.setText("Available");
                }
                else{
                    tue3.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        tue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleTue4();
                if(a.getTue4()){
                    tue4.setText("Available");
                }
                else{
                    tue4.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        tue5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleTue5();
                if(a.getTue5()){
                    tue5.setText("Available");
                }
                else{
                    tue5.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        tue6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleTue6();
                if(a.getTue6()){
                    tue6.setText("Available");
                }
                else{
                    tue6.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        wed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleWed1();
                if(a.getWed1()){
                    wed1.setText("Available");
                }
                else{
                    wed1.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        wed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleWed2();
                if(a.getWed2()){
                    wed2.setText("Available");
                }
                else{
                    wed2.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        wed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleWed3();
                if(a.getWed3()){
                    wed3.setText("Available");
                }
                else{
                    wed3.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        wed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleWed4();
                if(a.getWed4()){
                    wed4.setText("Available");
                }
                else{
                    wed4.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        wed5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleWed5();
                if(a.getWed5()){
                    wed5.setText("Available");
                }
                else{
                    wed5.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        wed6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleWed6();
                if(a.getWed6()){
                    wed6.setText("Available");
                }
                else{
                    wed6.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        thu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleThu1();
                if(a.getThu1()){
                    thu1.setText("Available");
                }
                else{
                    thu1.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        thu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleThu2();
                if(a.getThu2()){
                    thu2.setText("Available");
                }
                else{
                    thu2.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        thu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleThu3();
                if(a.getThu3()){
                    thu3.setText("Available");
                }
                else{
                    thu3.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        thu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleThu4();
                if(a.getThu4()){
                    thu4.setText("Available");
                }
                else{
                    thu4.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        thu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleThu5();
                if(a.getThu5()){
                    thu5.setText("Available");
                }
                else{
                    thu5.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        thu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleThu6();
                if(a.getThu6()){
                    thu6.setText("Available");
                }
                else{
                    thu6.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        fri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleFri1();
                if(a.getFri1()){
                    fri1.setText("Available");
                }
                else{
                    fri1.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        fri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleFri2();
                if(a.getFri2()){
                    fri2.setText("Available");
                }
                else{
                    fri2.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        fri3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleFri3();
                if(a.getFri3()){
                    fri3.setText("Available");
                }
                else{
                    fri3.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        fri4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleFri4();
                if(a.getFri4()){
                    fri4.setText("Available");
                }
                else{
                    fri4.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        fri5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleFri5();
                if(a.getFri5()){
                    fri5.setText("Available");
                }
                else{
                    fri5.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });
        fri6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.toggleFri6();
                if(a.getFri6()){
                    fri6.setText("Available");
                }
                else{
                    fri6.setText("Unavailable");
                }
                databaseReference.child(mUser.getUid()).setValue(a);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intHome = new Intent(AvailabilityActivity.this, HomeActivity.class);
                startActivity(intHome);
            }
        });

    }
}
