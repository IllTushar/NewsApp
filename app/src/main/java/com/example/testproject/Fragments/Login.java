package com.example.testproject.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testproject.Activities.HomeActivity;
import com.example.testproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Fragment {
private FirebaseAuth mAuth;
private EditText email,password;
    private Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        email = view.findViewById(R.id.loginEmail);
        password = view.findViewById(R.id.loginPassword);
        mAuth = FirebaseAuth.getInstance();
        login  = view.findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
        return view;
    }
    private void validation()
    {
        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Please wait..");
        pd.show();
        String Email = email.getText().toString().trim();
        String Password  =  password.getText().toString().trim();
        if (Email.isEmpty()){
            Toast.makeText(getContext(), "Enter Email !!", Toast.LENGTH_SHORT).show();
            pd.dismiss();
            return;
        }else if(Password.isEmpty()){
            Toast.makeText(getContext(), "Enter Password !!", Toast.LENGTH_SHORT).show();
            pd.dismiss();
            return;
        }
        else{
            mAuth.signInWithEmailAndPassword(Email,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()){
                              pd.dismiss();
                              Intent i = new Intent(getContext(), HomeActivity.class);
                              startActivity(i);
                              email.setText("");
                              password.setText("");
                          }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(getContext(), "Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}