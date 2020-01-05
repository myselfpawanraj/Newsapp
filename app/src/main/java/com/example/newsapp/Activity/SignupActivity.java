package com.example.newsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference users;
    EditText edtUsername,edtEmail,edtPassword;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        edtUsername = (EditText) findViewById(R.id.edtusername);
        edtPassword = (EditText) findViewById(R.id.edtpassword);
        edtEmail = (EditText) findViewById(R.id.edtmail);
        btnSignUp = (Button) findViewById(R.id.btnsignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user =new User(edtUsername.getText().toString(),
                        edtPassword.getText().toString(),
                        edtEmail.getText().toString());
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUsername()).exists())
                        Toast.makeText(SignupActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                        else {
                            if(user.getPassword().length()<5){
                                Toast.makeText(SignupActivity.this, "Password must be greater than 5 letters!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {users.child(user.getUsername()).setValue(user);
                            Toast.makeText(SignupActivity.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);}
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
