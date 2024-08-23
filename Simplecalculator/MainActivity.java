package com.example.simplecalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText numInput1,numInput2;
    private TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numInput1=findViewById(R.id.numInput1);
        numInput2=findViewById(R.id.numInput2);
        resultText=findViewById(R.id.resultText);
        findViewById(R.id.btnAdd).setOnClickListener(v->performCalculation('+'));
        findViewById(R.id.btnSubtract).setOnClickListener(v->performCalculation('-'));
        findViewById(R.id.btnMultiply).setOnClickListener(v->performCalculation('*'));
        findViewById(R.id.btnDivide).setOnClickListener(v->performCalculation('/'));

    }
    private void performCalculation(char operation)
    {
        try{
            double num1=Double.parseDouble(numInput1.getText().toString());
            double num2=Double.parseDouble(numInput2.getText().toString());
            double result;
            switch(operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        resultText.setText("Error:Division by zero");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    resultText.setText("Error:invalid operation");
                    return;
            }
            resultText.setText(String.format("Result:%.2f",result));

        }catch(NumberFormatException e){
            resultText.setText("Error:invalid input");
        }
    }
}
