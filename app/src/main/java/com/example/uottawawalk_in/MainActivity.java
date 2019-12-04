package com.example.uottawawalk_in;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText emailId,password,cpassword,firstName,lastName,studentNumber;
    private CheckBox employee,serviceProvider;
    private Button btnSignUp;
    private TextView tvSignIn;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();


        emailId=findViewById(R.id.email);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cpassword);
        firstName=findViewById(R.id.fName);
        lastName=findViewById(R.id.lName);
        studentNumber=findViewById(R.id.studentNumber);
        employee=findViewById(R.id.checkBox);
        btnSignUp=findViewById(R.id.button);
        tvSignIn = findViewById(R.id.textView);
        serviceProvider = findViewById(R.id.isServiceProvider);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String studentNum = studentNumber.getText().toString();
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String cpwd = cpassword.getText().toString();
                boolean isEmployee = employee.isChecked();
                final boolean isServiceProvider = serviceProvider.isChecked();
                System.out.println(isServiceProvider);


                if(email.trim().isEmpty() || pwd.trim().isEmpty() || cpwd.trim().isEmpty() || fName.trim().isEmpty() || lName.trim().isEmpty() || studentNum.trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter all fields.",Toast.LENGTH_SHORT ).show();
                }
                else if(pwd.length()<6){
                    password.setError("Please ensure that the password is longer than 5 characters");
                    password.requestFocus();
                }
                else if (!pwd.equals(cpwd)){
                    cpassword.setError("Please make sure the passwords match.");
                    cpassword.requestFocus();
                }
                else {
                    final Student mStudent = new Student(fName, lName, studentNum, email, pwd, isEmployee,isServiceProvider);
                    mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign Up UnSuccessful,Please Try again",Toast.LENGTH_SHORT ).show();

                            }
                            else {
                                mDatabase.getReference("Students").child(mAuth.getCurrentUser().getUid()).setValue(mStudent);
                                finish();
                                if(serviceProvider.isChecked()){
                                    System.out.println("Reached");
                                    Intent intServiceProfile = new Intent(MainActivity.this, AddServiceProfile.class);
                                    intServiceProfile.putExtra("UID",(mAuth.getCurrentUser().getUid()));
                                    startActivity(intServiceProfile);
                                }
                                else {
                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));//using intent to go to home activity
                                }
                            }
                        }
                    });
                }
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
