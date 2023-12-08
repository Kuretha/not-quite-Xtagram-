package com.slavery.xtag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, name, email, password, repass;
    private DatabaseReference mRootRef;
    private Button register_btn;
    private TextView loginUser;
    private FirebaseAuth mAuth;
    // TODO: change alert dialog to custom progress bar src: https://abhiandroid.com/ui/progressbar#gsc.tab=0
    AlertDialog.Builder ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username_reg_ui);
        name = findViewById(R.id.name_reg_ui);
        email = findViewById(R.id.email_reg_ui);
        password = findViewById(R.id.password_reg_ui);
        repass = findViewById(R.id.reenter_pass_reg_ui);
        register_btn = findViewById(R.id.register_btn);
        loginUser = findViewById(R.id.login_user_reg_ui);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        ab = new AlertDialog.Builder(this);

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textUsername = username.getText().toString();
                String textName = name.getText().toString();
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();
                // TODO: implement re enter password
//                String textRepass = repass.getText().toString();

                if (TextUtils.isEmpty(textUsername) || TextUtils.isEmpty(textUsername) ||
                TextUtils.isEmpty(textName) || TextUtils.isEmpty(textEmail)){
                    Toast.makeText(RegisterActivity.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                } else if (textPassword.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password are too short", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(textUsername, textName, textEmail, textPassword);
                }
            }
        });

    }

    private void registerUser(String username, String name, String email, String password) {
        ab.setMessage("Please Wait");
        ab.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("email", email);
                map.put("password", password);
                map.put("id", Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
                map.put("bio", "");
                map.put("imageUrl", "default");

                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Update the Profile",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}