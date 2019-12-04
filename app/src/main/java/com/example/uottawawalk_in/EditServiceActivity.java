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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditServiceActivity extends AppCompatActivity {
    private EditText serviceName;
    private EditText serviceRate;
    private TextView provider;
    private Spinner roleName;
    private Button editService,deleteService,backService;
    private DatabaseReference mDatabaseService, mDatabaseUser;
    private Query query;
    private int pos,id;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private ArrayList<Service> services;
    private ArrayAdapter<String> arrayAdapter;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        serviceName = findViewById(R.id.NameServiceEdit);
        serviceRate = findViewById(R.id.RateServiceEdit);
        provider = findViewById(R.id.Provider);
        roleName = findViewById(R.id.RoleSpinnerEdit);
        editService = findViewById(R.id.EditServiceButton);
        deleteService = findViewById(R.id.DeleteServiceButton);
        backService = findViewById(R.id.BackButtonEdit);
        services = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        arrayAdapter = new ArrayAdapter<String>(EditServiceActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.roles));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleName.setAdapter(arrayAdapter);

        mDatabaseService = FirebaseDatabase.getInstance().getReference("Services");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference();
        pos = Integer.valueOf(getIntent().getStringExtra("Position"));


        editService.setVisibility(View.GONE);
        deleteService.setVisibility(View.GONE);

        mDatabaseUser.child("Students").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                student = dataSnapshot.getValue(Student.class);
                if (!student.getIsServiceProvider() && !student.getIsEmployee() && !student.getAdmin()){
                    serviceName.setFocusable(false);
                    serviceRate.setFocusable(false);
                    roleName.setEnabled(false);
                    serviceName.setClickable(false);
                    serviceRate.setClickable(false);
                }
                else{
                    editService.setVisibility(View.VISIBLE);
                    deleteService.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*
        mDatabaseService.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                service = dataSnapshot.getValue(Service.class);
                System.out.println(student);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         */

        query = mDatabaseService.orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot a : dataSnapshot.getChildren()){
                    services.add(a.getValue(Service.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditServiceActivity.this,"Error Encountered",Toast.LENGTH_SHORT).show();
            }
        });
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                serviceName.setText(services.get(pos).getName());
                serviceRate.setText(Double.toString(services.get(pos).getRate()));
                provider.setText("Service provided by " + services.get(pos).getProvider().getFirstName());
                roleName.setSelection(arrayAdapter.getPosition(services.get(pos).getRole()));
                id = services.get(pos).getId();
            }
        };
        handler.postDelayed(r,500);
        backService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intService = new Intent(EditServiceActivity.this,ServiceActivity.class);
                startActivity(intService);
            }
        });
        editService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mDatabaseService.child(String.valueOf(id)).child("provider").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Student s = dataSnapshot.getValue(Student.class);
                        if (student.equals(s)){
                            if(serviceName.getText().toString().trim().isEmpty()){
                                serviceName.setError("Please do not leave the field blank");
                                serviceName.requestFocus();
                            }
                            else{
                                Service service = new Service(serviceName.getText().toString(),roleName.getSelectedItem().toString(),Double.parseDouble(serviceRate.getText().toString()), student,id-1);
                                mDatabaseService.child(String.valueOf(id)).setValue(service);
                                Toast.makeText(EditServiceActivity.this,"Service has been updated",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(EditServiceActivity.this,"You are not authorized to proceed",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseService.child(String.valueOf(id)).child("provider").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Student s = dataSnapshot.getValue(Student.class);
                        if (student.equals(s) || s.getAdmin()){
                            mDatabaseService.child(String.valueOf(id)).removeValue();
                            Toast.makeText(EditServiceActivity.this,"Service has been removed",Toast.LENGTH_SHORT).show();
                            Handler handler = new Handler();
                            Runnable ra = new Runnable() {
                                @Override
                                public void run() {
                                    Intent intService = new Intent(EditServiceActivity.this,ServiceActivity.class);
                                    startActivity(intService);
                                }
                            };
                            handler.postDelayed(ra,500);
                        }
                        else
                            Toast.makeText(EditServiceActivity.this,"You are not authorized to proceed",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}
