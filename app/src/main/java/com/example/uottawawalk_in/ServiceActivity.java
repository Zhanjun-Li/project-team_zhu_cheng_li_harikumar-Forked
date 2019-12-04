package com.example.uottawawalk_in;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {
    private Button newService,returnHome;
    private ArrayList<Service> services;
    private ArrayAdapter<Service> adapter;
    private ListView listView;
    DatabaseReference databaseService,mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private boolean isEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        databaseService = FirebaseDatabase.getInstance().getReference("Services");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        services = new ArrayList<>();
        newService = findViewById(R.id.newService);
        returnHome = findViewById(R.id.buttonHome);
        listView = findViewById(R.id.layout_service_list);

        adapter = new ArrayAdapter<>(ServiceActivity.this, android.R.layout.simple_list_item_1,services);
        listView.setAdapter(adapter);

        newService.setVisibility(View.GONE);

        mDatabase.child("Students").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
                isEmployee = student.getIsEmployee();
                if (student.getIsEmployee() || student.getIsServiceProvider() || student.getAdmin())
                    newService.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseService.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Service value = dataSnapshot.getValue(Service.class);
                services.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intEdit = new Intent(ServiceActivity.this, EditServiceActivity.class);
                intEdit.putExtra("Position",String.valueOf(position));
                startActivity(intEdit);
            }
        });
        newService.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if (isEmployee){
                    Intent addService = new Intent(ServiceActivity.this,AddServiceActivity.class);
                    startActivity(addService);
                }
                else{
                    Toast.makeText(ServiceActivity.this,"You are no authorized to proceed.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        returnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intHome = new Intent(ServiceActivity.this,HomeActivity.class);
                startActivity(intHome);
            }
        });
    }
}
