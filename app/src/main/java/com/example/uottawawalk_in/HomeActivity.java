package com.example.uottawawalk_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private boolean isServiceProvider = false;

    TextView textView;
    Button btnLogout, btnServices, btnAvailability,btnSearchForClinic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        textView = findViewById(R.id.WelcomeText);

        if (mUser == null) {
            finish();
            Intent intMain = new Intent(HomeActivity.this,MainActivity.class);
            startActivity( intMain );
        }
        else {
            String uid = mUser.getUid();
            mDatabase.child("Students")
                    .child(mUser.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Student student = dataSnapshot.getValue(Student.class);
                            isServiceProvider = student.getIsServiceProvider();
                            if (student.getIsEmployee())
                                textView.setText("Welcome " + student.getFirstName() + ".\nYou are an employee.");
                            else
                                textView.setText("Welcome " + student.getFirstName() + ".");
                            System.out.println();
                            if (student.getAdmin())
                                btnServices.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
        }



        btnLogout = findViewById(R.id.logout);
        btnServices = findViewById(R.id.buttonServices);
        btnAvailability = findViewById(R.id.btnAvailability);
        btnSearchForClinic=findViewById(R.id.btnSearchForClinic);
        //btnServices.setVisibility(View.GONE); Who added this line???

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intMain = new Intent(HomeActivity.this,MainActivity.class);
                startActivity( intMain );
            }
        });

        btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intService = new Intent(HomeActivity.this,ServiceActivity.class);
                startActivity(intService);
            }
        });


        btnSearchForClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intSearchClinic = new Intent(HomeActivity.this,SearchClinicActivity.class);
                startActivity(intSearchClinic);
            }
        });



        btnAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isServiceProvider){
                Intent intAvailability = new Intent(HomeActivity.this, AvailabilityActivity.class);
                startActivity(intAvailability);
                }
                else{
                    Toast.makeText(HomeActivity.this,"You are not authorized to proceed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

