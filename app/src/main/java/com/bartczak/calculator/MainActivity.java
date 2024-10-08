package com.bartczak.calculator;

import androidx.appcompat.app.ActionBar;
import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_main);

        Button aboutButton = findViewById(R.id.aboutButton);
        Button simpleButton  = findViewById(R.id.simpleButton);
        Button advancedButton = findViewById(R.id.advancedButton);
        Button exitButton = findViewById(R.id.exitButton);

        aboutButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });

        simpleButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, SimpleCalculatorActivity.class);
            startActivity(intent);
        });

        advancedButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AdvancedCalculatorActivity.class);
            startActivity(intent);
        });

        exitButton.setOnClickListener((view) -> {
            finish();
        });
    }
}