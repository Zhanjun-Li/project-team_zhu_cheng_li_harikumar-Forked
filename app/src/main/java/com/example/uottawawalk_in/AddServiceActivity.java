package com.example.uottawawalk_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddServiceActivity extends AppCompatActivity {
    private Button addService,back;
    private EditText serviceName;
    private EditText serviceRate;
    private Spinner role;
    private ArrayAdapter<String> arrayAdapter;
    private int currentId;
    private String test;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Student student;
    DatabaseReference mDatabaseService, mDatabaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        mDatabaseService = FirebaseDatabase.getInstance().getReference("Services");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference();

        addService = (Button) findViewById(R.id.addServiceButton);
        back = (Button) findViewById(R.id.backButton);
        serviceName = (EditText) findViewById(R.id.addServiceName);
        serviceRate = (EditText) findViewById(R.id.addServiceRate);
        role = (Spinner) findViewById(R.id.addServiceRole);
        arrayAdapter = new ArrayAdapter<String>(AddServiceActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.roles));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(arrayAdapter);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabaseUser.child("Students").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                student = dataSnapshot.getValue(Student.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query query = mDatabaseService.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot a : dataSnapshot.getChildren()){
                    currentId = Integer.valueOf(a.child("id").getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AddServiceActivity.this,"Error Encountered",Toast.LENGTH_SHORT).show();
            }
        });

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceName.getText().toString().trim().isEmpty()){
                    serviceName.setError("Please enter a name");
                }
                if(serviceRate.getText().toString().trim().isEmpty()) {
                    serviceRate.setError("Please enter a rate");
                }
                if (!(serviceName.getText().toString().trim().isEmpty() || serviceRate.getText().toString().trim().isEmpty())){
                    Service service = new Service(serviceName.getText().toString(),role.getSelectedItem().toString(),Double.parseDouble(serviceRate.getText().toString()), student,currentId);
                    mDatabaseService.child(String.valueOf(service.getId())).setValue(service);
                    currentId++;
                    Toast.makeText(AddServiceActivity.this,"Service added successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backService = new Intent(AddServiceActivity.this,ServiceActivity.class);
                startActivity(backService);
            }
        });
    }
}
