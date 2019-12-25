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

public class login extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();



        final EditText name = (EditText) findViewById(R.id.username);
        final EditText pass = (EditText) findViewById(R.id.password);
        final Button btnRegister = (Button) findViewById(R.id.btnreg);
        final Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        final Button guest = (Button) findViewById(R.id.guest);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, home.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameUser = (String) name.getText().toString();
                final String passUser = (String) pass.getText().toString();

                signIn(nameUser,
                        passUser);
            }
        });
    }

    private void signIn(final String username, final String password) {
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if(login.getPassword().equals(password)){
                            Toast.makeText(login.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, home.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(login.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {

                    Toast.makeText(login.this, "Username not registered!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
