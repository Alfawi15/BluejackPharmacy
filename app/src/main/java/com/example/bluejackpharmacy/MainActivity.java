package com.example.bluejackpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActivityM

    private EditText ETEmail;
    private EditText ETPassword;
    private Button BTLogin;
    private Button BTRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETEmail = findViewById(R.id.ETEmail);
        ETPassword = findViewById(R.id.ETPassword);
        BTLogin = findViewById(R.id.BTLogin);
        BTRegister = findViewById(R.id.BTRegister);

        BTLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginEmail();
            }

        });
        BTRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainRegister.class);
                startActivity(intent);
            }
        });
    }
    private void loginEmail(){

        String email = ETEmail.getText().toString();
        String password = ETPassword.getText().toString();
        boolean valid = true;

        if(TextUtils.isEmpty(email)){
            valid = false;
            Toast.makeText(getApplicationContext(), "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            valid = false;
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }if(valid){
            Intent intent = new Intent(MainActivity.this, MainHome.class);
            startActivity(intent);
        }
    }

}