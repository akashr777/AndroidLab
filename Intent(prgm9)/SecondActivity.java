package com.example.intend;
import static com.example.intend.R.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    TextView a1,a2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_second);
        a1 = findViewById(R.id.n1);
        a2 = findViewById(R.id.n2);

        Bundle extra = getIntent().getExtras();
        String text = extra.getString("name");
        String text1 = extra.getString("name1");
        a1.setText(text);
        a2.setText(text1);


    }
}