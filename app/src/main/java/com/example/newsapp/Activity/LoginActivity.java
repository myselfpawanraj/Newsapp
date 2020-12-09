package com.example.newsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference users;
    CheckBox checkBox;
    String username;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        checkloggedin();
        final EditText name = (EditText) findViewById(R.id.username);
        final EditText pass = (EditText) findViewById(R.id.password);
        final Button btnRegister = (Button) findViewById(R.id.btnreg);
        final Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        loading = (ProgressBar) findViewById(R.id.loading);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameUser = (String) name.getText().toString();
                final String passUser = (String) pass.getText().toString();
                username=nameUser;
                loading.setVisibility(View.VISIBLE); //to show
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
                            setUsername();
                            Toast.makeText(LoginActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                            if (checkBox.isChecked()) {
                                stayloggedin();
                            }
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.INVISIBLE);
                        }
                    }
                    else {
                        Toast.makeText( LoginActivity.this, "Input Username!", Toast.LENGTH_SHORT ).show();
                        loading.setVisibility( View.INVISIBLE );
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Username not registered!", Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void stayloggedin(){
        SharedPreferences sharedPref = getSharedPreferences("CheckLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login", "true");
        editor.apply();
    }


    public void checkloggedin(){
        SharedPreferences sharedPref = getSharedPreferences("CheckLogin", Context.MODE_PRIVATE);
        String check =sharedPref.getString("login","false" );
        if(check.equals("true")){
            Toast.makeText(LoginActivity.this, "Logging using previous data!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
    public void setUsername(){
        SharedPreferences sharedPref = getSharedPreferences("A", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", username);
        editor.apply();
    }
}
