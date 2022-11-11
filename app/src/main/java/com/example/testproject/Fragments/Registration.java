package com.example.testproject.Fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testproject.Activities.HomeActivity;
import com.example.testproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registration extends Fragment {
private EditText name,email,password,phone;
private CheckBox checkBox;
private Button btnRegistration;
private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        name = view.findViewById(R.id.Name);
        email = view.findViewById(R.id.Email);
        password = view.findViewById(R.id.Password);
        checkBox =view.findViewById(R.id.checkbox);
        phone = view.findViewById(R.id.Phone);
        mAuth = FirebaseAuth.getInstance();
        btnRegistration = view.findViewById(R.id.registration);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkBox.isChecked())){
                    Toast.makeText(getContext(), "Please checked the box !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    validation();
                }

            }
        });
        return view;
    }

    private void validation()
    {
        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Please wait..");
        pd.show();
        String Name = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        if (Name.isEmpty()){
            Toast.makeText(getContext(), "Enter Name !!", Toast.LENGTH_SHORT).show();
            pd.dismiss();

        }
        else if(Email.isEmpty()){
            Toast.makeText(getContext(), "Enter Email !!", Toast.LENGTH_SHORT).show();
            pd.dismiss();

        }
        else if(Phone.isEmpty()){
            Toast.makeText(getContext(), "Enter Phone No !!", Toast.LENGTH_SHORT).show();
            pd.dismiss();

        }
        else if(Phone.length()!=10){
            Toast.makeText(getContext(), "Phone Length is 10 digits", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
        else if(Password.isEmpty()){
            Toast.makeText(getContext(), "Enter Password !!", Toast.LENGTH_SHORT).show();
            pd.dismiss();

        }
        else{
            mAuth.createUserWithEmailAndPassword(Email,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                pd.dismiss();
                                Intent i = new Intent(getContext(), HomeActivity.class);
                                startActivity(i);
                               name.setText("");
                               email.setText("");
                               password.setText("");
                               phone.setText("");
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Some things went wrong !!", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    });
        }
    }
}