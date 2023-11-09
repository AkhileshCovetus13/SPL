package com.example.spl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.spl.programmer.ActivityProgrammer;
import com.example.spl.psa.ActivityPSA;

import java.util.Objects;

public class ActivityChooseOperation extends AppCompatActivity {

    Button btnPsa, btnProgrammer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_operation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        btnPsa = findViewById(R.id.psa);
        btnProgrammer = findViewById(R.id.prog);


        btnPsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityChooseOperation.this, ActivityPSA.class);
                startActivity(intent);
            }
        });


        btnProgrammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityChooseOperation.this, ActivityProgrammer.class);
                startActivity(intent);
            }
        });
    }

}