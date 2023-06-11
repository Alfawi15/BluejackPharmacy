package com.example.bluejackpharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bluejackpharmacy.databinding.ActivityMainRegisterBinding;

public class MainRegister extends AppCompatActivity {

    ActivityMainRegisterBinding binding;
    DatabaseHelper databaseHelper;

    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextPhoneNumber;
    private Button buttonRegister;
    private Button buttonBackLogin;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            validasi();
            String FullName = binding.editTextFullName.getText().toString();
            String Email = binding.editTextEmail.getText().toString();
            String Password = binding.editTextPassword.getText().toString();
            String ConfirmPassword = binding.editTextConfirmPassword.getText().toString();
            int PhoneNumber = Integer.parseInt(binding.editTextPhoneNumber.getText().toString());

            if (FullName.equals("")|| Email.equals("")||Password.equals("")||ConfirmPassword.equals("") );
                Toast.makeText(MainRegister.this, "all fields are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    if (Password.equals(editTextConfirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(Email);

                        if (checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(Email,Password);

                            if(insert == true){
                                Toast.makeText(MainRegister.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivities(intent);
                            }else {
                                Toast.makeText(MainRegister.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(MainRegister.this, "User allready exist, please login",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
        );


        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonBackLogin = findViewById(R.id.buttonBackLogin);

//        buttonRegister.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                validasi();
////            }
////        });

        buttonBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainRegister.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void validasi(){
        String name = editTextFullName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPW = editTextConfirmPassword.getText().toString();
        String phoneNum = editTextPhoneNumber.getText().toString();

        boolean valid = true;
        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPW.isEmpty() || phoneNum.isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(), "all fields must be filled", Toast.LENGTH_SHORT).show();
        } else if (name.length() < 5) {
            valid = false;
            Toast.makeText(getApplicationContext(), "name length at least five characters.", Toast.LENGTH_SHORT).show();
        } else if (!email.endsWith(".com")) {
            valid = false;
            Toast.makeText(getApplicationContext(), "email must end with .com", Toast.LENGTH_SHORT).show();
        } else if (!password.matches("[a-zA-Z0-9]+")) {
            valid = false;
            Toast.makeText(getApplicationContext(), "password must be alphanumeric.", Toast.LENGTH_SHORT).show();
        } else if (valid) {
            Intent intent = new Intent(MainRegister.this, MainHome.class);
            startActivity(intent);
        }
    }
}