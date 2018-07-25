package com.apress.gerber.currencies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //define members that correspond to Views in our layout
    private Button mCalcButton;
    private TextView mConvertedTextView;
    private EditText mAmountEditText;
    private Spinner mForSpinner, mHomSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign references to our Views
        mConvertedTextView = findViewById(R.id.txt_converted);
        mAmountEditText = findViewById(R.id.edt_amount);
        mCalcButton = findViewById(R.id.btn_calc);
        mForSpinner = findViewById(R.id.spn_for);
        mHomSpinner = findViewById(R.id.spn_hom);

    }
}
