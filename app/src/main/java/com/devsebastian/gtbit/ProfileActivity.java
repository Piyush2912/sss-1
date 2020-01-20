package com.devsebastian.gtbit;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextInputEditText nameEt, mobileEt, emailEt, ageEt;
    private Button submitBtn;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_back );
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nameEt = findViewById(R.id.name);
        mobileEt = findViewById(R.id.phone);
        emailEt = findViewById(R.id.email);
        ageEt = findViewById(R.id.age);
        submitBtn = findViewById(R.id.next);

        databaseReference.child("users").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    nameEt.setText(dataSnapshot.child("name").getValue(String.class));
                    ageEt.setText(dataSnapshot.child("age").getValue(String.class));
                    mobileEt.setText(dataSnapshot.child("phone").getValue(String.class));
                    emailEt.setText(dataSnapshot.child("email").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(nameEt.getText().toString())){
                    nameEt.setError("Enter name");
                    return;
                }

                if(TextUtils.isEmpty(mobileEt.getText().toString())){
                    mobileEt.setError("Enter mobile");
                    return;
                }

                if(TextUtils.isEmpty(ageEt.getText().toString())){
                    ageEt.setError("Enter age");
                    return;
                }

                if(TextUtils.isEmpty(emailEt.getText().toString())){
                    emailEt.setError("Enter email");
                    return;
                }

                databaseReference.child("users").child("1").child("name").setValue(nameEt.getText().toString());
                databaseReference.child("users").child("1").child("phone").setValue(mobileEt.getText().toString());
                databaseReference.child("users").child("1").child("email").setValue(emailEt.getText().toString());
                databaseReference.child("users").child("1").child("age").setValue(ageEt.getText().toString());

                Toast.makeText(ProfileActivity.this,"Changes saved",Toast.LENGTH_LONG).show();
            }
        });

    }
}
