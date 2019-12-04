package com.example.uottawawalk_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddServiceProfile extends AppCompatActivity {
    private EditText address,companyName,phoneNumber,description;
    private CheckBox licensed;
    private Button finishRegistration;
    private String uID;
    private DatabaseReference databaseReference,availabilityReference;
    private Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference("Students");
        availabilityReference = FirebaseDatabase.getInstance().getReference("Availability");
        address =findViewById(R.id.addAddress);
        companyName=findViewById(R.id.addCompany);
        phoneNumber=findViewById(R.id.addPhone);
        description=findViewById(R.id.addDescription);
        licensed=findViewById(R.id.isLicensed);
        finishRegistration=findViewById(R.id.btnGoHome);
        uID = getIntent().getStringExtra("UID");

        Query query = databaseReference.child(uID);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                student = dataSnapshot.getValue(Student.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };
        handler.postDelayed(r,500);
        finishRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(address.getText().toString().trim().isEmpty()){
                    address.setError("Please do not leave the field blank.");
                    address.requestFocus();
                }
                else if(phoneNumber.getText().toString().trim().isEmpty()){
                    phoneNumber.setError("Please do not leave the field blank.");
                    phoneNumber.requestFocus();
                }
                else if(companyName.getText().toString().trim().isEmpty()){
                    companyName.setError("Please do not leave the field blank.");
                    companyName.requestFocus();
                }
                else{
                    student.setAddress(address.getText().toString().trim());
                    student.setPhoneNumber(phoneNumber.getText().toString().trim());
                    student.setCompanyName(companyName.getText().toString().trim());
                    student.setGeneralDescription(description.getText().toString().trim());
                    student.setIsLicensed(licensed.isChecked());
                    databaseReference.child(uID).setValue(student);
                    Availability a = new Availability();
                    availabilityReference.child(uID).setValue(a);
                    Intent goGome = new Intent(AddServiceProfile.this,HomeActivity.class);
                    startActivity(goGome);
                }
            }
        });

    }
}
