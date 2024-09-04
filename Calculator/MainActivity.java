package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentExpression = "";
    private Double operand1 = null;
    private String operator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] buttonIds = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9, R.id.button_add, R.id.button_subtract,
                R.id.button_multiply, R.id.button_divide, R.id.button_clear, R.id.button_equals
        };

        String[] buttonTexts = {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "C", "="
        };

        for (int i = 0; i < buttonIds.length; i++) {
            final String text = buttonTexts[i];
            Button button = findViewById(buttonIds[i]);
            button.setOnClickListener(v -> onButtonClick(text));
        }
    }

    private void onButtonClick(String text) {
        try {
            switch (text) {
                case "C":
                    currentExpression = "";
                    operand1 = null;
                    operator = null;
                    display.setText("");
                    break;
                case "=":
                    Double operand2 = parseDouble(getLastOperand(currentExpression));
                    if (operand1 != null && operator != null && operand2 != null) {
                        Double result = null;
                        switch (operator) {
                            case "+":
                                result = operand1 + operand2;
                                break;
                            case "-":
                                result = operand1 - operand2;
                                break;
                            case "*":
                                result = operand1 * operand2;
                                break;
                            case "/":
                                if (operand2 != 0) {
                                    result = operand1 / operand2;
                                } else {
                                    display.setText("Error");
                                    return;
                                }
                                break;
                        }
                        currentExpression += "=" + result;
                        display.setText(currentExpression);
                        operand1 = result;
                        operator = null;
                    }
                    break;
                default:
                    if ("+-*/".contains(text)) {
                        if (operator != null) {
                            // Replace the previous operator with the new one
                            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                        }
                        operand1 = parseDouble(getLastOperand(currentExpression));
                        operator = text;
                        currentExpression += operator;
                    } else {
                        currentExpression += text;
                    }
                    display.setText(currentExpression);
                    break;
            }
        } catch (Exception e) {
            display.setText("Error");
            e.printStackTrace(); // Log the exception to Logcat
        }
    }

    private String getLastOperand(String expression) {
        // Split the expression by operators and get the last operand
        String[] parts = expression.split("\\+|-|\\*|/");
        return parts[parts.length - 1];
    }

    private Double parseDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

