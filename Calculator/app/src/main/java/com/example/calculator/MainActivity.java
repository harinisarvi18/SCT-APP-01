package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvInput;
    private String input = "";
    private String operator = "";
    private Double firstValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();

                switch (buttonText) {
                    case "C":
                        input = "";
                        operator = "";
                        firstValue = null;
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        operator = buttonText;
                        firstValue = Double.parseDouble(input);
                        input = "";
                        break;
                    case "=":
                        if (firstValue != null && !input.isEmpty()) {
                            double secondValue = Double.parseDouble(input);
                            input = calculate(firstValue, secondValue, operator);
                            operator = "";
                            firstValue = null;
                        }
                        break;
                    default:
                        input += buttonText;
                        break;
                }
                tvInput.setText(input.isEmpty() ? "0" : input);
            }
        };

        // Set the listener to all the buttons
        int[] buttonIds = new int[]{
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply,
                R.id.btnDivide, R.id.btnEquals, R.id.btnClear, R.id.btnDot
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private String calculate(double firstValue, double secondValue, String operator) {
        switch (operator) {
            case "+":
                return String.valueOf(firstValue + secondValue);
            case "-":
                return String.valueOf(firstValue - secondValue);
            case "*":
                return String.valueOf(firstValue * secondValue);
            case "/":
                return secondValue != 0 ? String.valueOf(firstValue / secondValue) : "Error";
            default:
                return "0";
        }
    }
}
