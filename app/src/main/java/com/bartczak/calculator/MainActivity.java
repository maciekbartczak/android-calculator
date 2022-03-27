package com.bartczak.calculator;

import androidx.appcompat.app.ActionBar;
import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button aboutButton;
    private Button simpleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_main);

        aboutButton = findViewById(R.id.aboutButton);
        simpleButton = findViewById(R.id.simpleButton);

        aboutButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });

        simpleButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, SimpleCalculatorActivity.class);
            startActivity(intent);
        });
    }
}