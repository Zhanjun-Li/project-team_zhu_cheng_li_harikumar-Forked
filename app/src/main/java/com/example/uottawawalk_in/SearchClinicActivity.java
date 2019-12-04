package com.example.uottawawalk_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SearchClinicActivity extends AppCompatActivity {

    private DatabaseReference mUserDatabase;
    private EditText mSearchFieldAddress;
    private Button btnSearch,btnBack;
    private Spinner role;
    private ListView clinicsDisplay;
    private ArrayList<String> clinicStorage, roles;
    private ArrayAdapter<Student> adapter;
    private ArrayList<Student> listOfClinics;
    private ArrayList<Student> validClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_clinic);

        mSearchFieldAddress=findViewById(R.id.tvAddress);
        role = findViewById(R.id.spRole);
        clinicsDisplay = findViewById(R.id.lvClinics);
        btnSearch= findViewById(R.id.btnSearch);
        btnBack.findViewById(R.id.btnBack);
        mUserDatabase= FirebaseDatabase.getInstance().getReference("Student");
        listOfClinics = new ArrayList<>();
        validClinics = new ArrayList<>();
        roles = new ArrayList<>();


        roles.add("Any");
        roles.add("Doctor");
        roles.add("Nurse");
        roles.add("Staff");
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(SearchClinicActivity.this,android.R.layout.simple_list_item_1,roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter = new ArrayAdapter<>(SearchClinicActivity.this,android.R.layout.simple_list_item_1,validClinics);
        role.setAdapter(roleAdapter);

        Query query = mUserDatabase;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student temp = dataSnapshot.getValue(Student.class);
                if(temp.getIsServiceProvider()){
                    listOfClinics.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Handler handler = new Handler();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                listOfClinics.toString();
            }
        };
        handler.postDelayed(r1, 500);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intHome = new Intent(SearchClinicActivity.this,HomeActivity.class);
                startActivity(intHome);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = mSearchFieldAddress.getText().toString();

            }
        });
    }
}
