package com.example.uottawawalk_in;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailId,password;
    Button btnSignIn;
    TextView tvSignUP;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();
        emailId=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btnSignIn=findViewById(R.id.button);
        tvSignUP = findViewById(R.id.textView);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser= mAuth.getCurrentUser();
                if ( mFirebaseUser == null){
                    Toast.makeText(LoginActivity.this,"Please Login ",Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email= emailId.getText().toString();
                String pwd= password.getText().toString();

               /* mAuth.signInWithEmailAndPassword("z@z.com","zzzzzz").addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Log in Error, Please Log in Again",Toast.LENGTH_SHORT ).show();// error message

                        }
                        else{
                            Intent intoToHome = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intoToHome);
                        }

                    }
                });*/

                //quickly log in to a temp account
                mAuth.signInWithEmailAndPassword("ste2@test.com","123qwe").addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            Intent intoToHome = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intoToHome);
                    }
                });

                /*
                if(email.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please enter all fields",Toast.LENGTH_SHORT ).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Log in Error, Please Log in Again",Toast.LENGTH_SHORT ).show();// error message

                            }
                            else{
                                Intent intoToHome = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intoToHome);
                            }

                        }
                    });
                }
                 */

            }
        });
        tvSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intSignUp);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
