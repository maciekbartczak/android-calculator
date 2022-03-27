package com.bartczak.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;

public class SimpleCalculatorActivity extends AppCompatActivity {

    private String expression = "";
    private TextView output;
    private final ArrayList<Button> inputButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_simple_calculator);

        inputButtons.add(findViewById(R.id.button0));
        inputButtons.add(findViewById(R.id.button1));
        inputButtons.add(findViewById(R.id.button2));
        inputButtons.add(findViewById(R.id.button3));
        inputButtons.add(findViewById(R.id.button4));
        inputButtons.add(findViewById(R.id.button5));
        inputButtons.add(findViewById(R.id.button6));
        inputButtons.add(findViewById(R.id.button7));
        inputButtons.add(findViewById(R.id.button8));
        inputButtons.add(findViewById(R.id.button9));
        inputButtons.add(findViewById(R.id.button_plus));
        inputButtons.add(findViewById(R.id.button_minus));
        inputButtons.add(findViewById(R.id.button_times));
        inputButtons.add(findViewById(R.id.button_div));
        inputButtons.add(findViewById(R.id.button_comma));

        Button clearButton = findViewById(R.id.button_c);
        Button backspaceButton = findViewById(R.id.button_bksp);
        Button equalsButton = findViewById(R.id.button_equals);
        Button signButton = findViewById(R.id.button_sign);

        output = findViewById(R.id.output);
        output.setSelected(true);

        inputButtons.forEach(button -> button.setOnClickListener((view) -> {
            final String value = button.getText().toString();
            if (expression.contains("NaN")) {
                expression = "";
            }
            expression += value;
            output.setText(expression);
        }));

        clearButton.setOnClickListener(view -> {
            expression = "";
            output.setText(expression);
        });

        backspaceButton.setOnClickListener(view -> {
            if (expression.length() > 0) {
                expression = expression.substring(0, expression.length() - 1);
                output.setText(expression);
            }
        });

        equalsButton.setOnClickListener(view -> {
            Expression expression = new Expression(this.expression);
            double result = expression.calculate();
            this.expression = Double.toString(result);
            if (Double.isNaN(result)) {
                Toast toast = Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT);
                toast.show();
            }
            output.setText(this.expression);

        });

        signButton.setOnClickListener(view -> {
            if (expression.length() > 0) {
                if (expression.charAt(0) == '-') {
                    expression = expression.substring(1);
                }
                else {
                    expression = "-" + expression;
                }
            }
            output.setText(expression);
        });

    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("expression", expression);
        super.onSaveInstanceState(savedInstanceState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        expression = savedInstanceState.getString("expression");
        output.setText(expression);
    }

}