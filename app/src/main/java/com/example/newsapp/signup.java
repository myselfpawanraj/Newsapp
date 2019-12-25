package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
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
                        Toast.makeText(signup.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                        else {
                            if(user.getPassword().length()<5){
                                Toast.makeText(signup.this, "Password must be greater than 5 letters!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {users.child(user.getUsername()).setValue(user);
                            Toast.makeText(signup.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signup.this, login.class);
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
