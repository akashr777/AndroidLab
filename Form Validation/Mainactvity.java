package com.example.formvalidation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bSubmit;
    EditText etFirstName, etLastName, etPassword;
    RadioGroup genderRadioGroup;
    Switch newMemberSwitch;
    CheckBox acceptTermsCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        bSubmit = findViewById(R.id.SubmitButton);
        etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.lastName);
        etPassword = findViewById(R.id.password);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        newMemberSwitch = findViewById(R.id.newMemberSwitch);
        acceptTermsCheckbox = findViewById(R.id.acceptTermsCheckbox);

        // Set up click listeners
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckAllFields()) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
            }
        });

        // Set up TextWatchers for real-time feedback
        etFirstName.addTextChangedListener(new FieldTextWatcher("First Name"));
        etLastName.addTextChangedListener(new FieldTextWatcher("Last Name"));
        etPassword.addTextChangedListener(new FieldTextWatcher("Password"));

        // Set up listeners for other controls
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(MainActivity.this, "Gender selected", Toast.LENGTH_SHORT).show();
            }
        });

        newMemberSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "New Member Selected" : "New Member Deselected";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        });

        acceptTermsCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Terms Accepted" : "Terms Not Accepted";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }

    private boolean CheckAllFields() {
        // Check First Name
        if (etFirstName.length() == 0) {
            etFirstName.setError("This field is required");
            return false;
        }

        // Check Last Name
        if (etLastName.length() == 0) {
            etLastName.setError("This field is required");
            return false;
        }

        // Check Password
        if (etPassword.length() == 0) {
            etPassword.setError("Password is required");
            return false;
        } else if (etPassword.length() < 8) {
            etPassword.setError("Password must be minimum 8 characters");
            return false;
        }

        // Check Gender RadioGroup
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check Accept Terms Checkbox
        if (!acceptTermsCheckbox.isChecked()) {
            Toast.makeText(this, "You must accept the terms and conditions", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private class FieldTextWatcher implements TextWatcher {
        private final String fieldName;

        FieldTextWatcher(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No action needed
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Show a Toast for each text field change
            Toast.makeText(MainActivity.this, fieldName + " field updated", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // No action needed
        }
    }
}

