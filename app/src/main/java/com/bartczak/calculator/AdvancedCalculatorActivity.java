package com.bartczak.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;

public class AdvancedCalculatorActivity extends AppCompatActivity {

    private String expression = "";
    private TextView output;
    private final ArrayList<Button> inputButtons = new ArrayList<>();
    private final ArrayList<Button> advancedInputButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_advanced_calculator);

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
        inputButtons.add(findViewById(R.id.button_left_bracket));
        inputButtons.add(findViewById(R.id.button_right_bracket));
        inputButtons.add(findViewById(R.id.button_percent));

        advancedInputButtons.add(findViewById(R.id.button_sin));
        advancedInputButtons.add(findViewById(R.id.button_cos));
        advancedInputButtons.add(findViewById(R.id.button_tan));
        advancedInputButtons.add(findViewById(R.id.button_log));
        advancedInputButtons.add(findViewById(R.id.button_ln));
        advancedInputButtons.add(findViewById(R.id.button_sqrt));

        Button clearButton = findViewById(R.id.button_c);
        Button backspaceButton = findViewById(R.id.button_bksp);
        Button equalsButton = findViewById(R.id.button_equals);
        Button signButton = findViewById(R.id.button_sign);

        Button power2Button = findViewById(R.id.button_power_2);
        Button powerButton = findViewById(R.id.button_power);

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

        advancedInputButtons.forEach(button -> button.setOnClickListener((view) -> {
            final String value = button.getText().toString();
            if (expression.contains("NaN")) {
                expression = "";
            }
            expression += value + "(";
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

        power2Button.setOnClickListener(view -> {
            expression += "^2";
            output.setText(expression);
        });

        powerButton.setOnClickListener(view -> {
            expression += "^";
            output.setText(expression);
        } );

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("expression", expression);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        expression = savedInstanceState.getString("expression");
        output.setText(expression);
    }
}