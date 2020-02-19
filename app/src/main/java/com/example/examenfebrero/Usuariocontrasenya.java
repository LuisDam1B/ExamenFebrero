package com.example.examenfebrero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Usuariocontrasenya extends Fragment {
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Button button;
    String email, password;
    View rootView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        rootView = inflater.inflate(R.layout.usuariocontrasenya, container, false);


        mAuth = FirebaseAuth.getInstance();

        rootView.findViewById(R.id.btnsignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = ((EditText) rootView.findViewById(R.id.edtemail)).getText().toString();
                password = ((EditText) rootView.findViewById(R.id.edtpassword)).getText().toString();
                if (email != "" && password != "")
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        ((MainActivity)getActivity()).cargarFragment(new Recycler());
                                    } else {
                                        Toast.makeText(getActivity(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
            }
        });
        return rootView;
    }

}





