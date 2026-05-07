package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnit, toUnit;
    Button convertBtn;
    TextView resultText;

    String[] units = {"Meter", "Kilometer", "Centimeter", "Gram", "Kilogram"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        convertBtn = findViewById(R.id.convertBtn);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                units
        );

        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valueStr = inputValue.getText().toString();

                if (valueStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter value", Toast.LENGTH_SHORT).show();
                    return;
                }

                double value = Double.parseDouble(valueStr);
                String from = fromUnit.getSelectedItem().toString();
                String to = toUnit.getSelectedItem().toString();

                double result = convert(value, from, to);

                resultText.setText("Result: " + result);
            }
        });
    }

    private double convert(double value, String from, String to) {

        // Convert everything to base unit (Meter or Gram)
        double base;

        // Length
        if (from.equals("Meter")) base = value;
        else if (from.equals("Kilometer")) base = value * 1000;
        else if (from.equals("Centimeter")) base = value / 100;

            // Weight
        else if (from.equals("Gram")) base = value;
        else if (from.equals("Kilogram")) base = value * 1000;

        else return 0;

        // Convert base to target
        if (to.equals("Meter")) return base;
        else if (to.equals("Kilometer")) return base / 1000;
        else if (to.equals("Centimeter")) return base * 100;

        else if (to.equals("Gram")) return base;
        else if (to.equals("Kilogram")) return base / 1000;

        return 0;
    }
}