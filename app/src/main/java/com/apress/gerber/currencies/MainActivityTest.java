package com.apress.gerber.currencies;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;
    private Button mCalcButton;
    private TextView mConvertedTextView;
    private EditText mAmountEditText;
    private Spinner mForSpinner, mHomSpinner;

    public MainActivityTest(){
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        //pass bogus currencies
        ArrayList<String> bogusCurrencies = new ArrayList<String>();
        bogusCurrencies.add("USD|United States Dollar");
        bogusCurrencies.add("EUR|Euro");
        Intent intent = new Intent();
        intent.putExtra(SplashActivity.KEY_ARRAYLIST, bogusCurrencies);
        setActivityIntent(intent);

        //get the activity under test
        mActivity = getActivity();
        //assign references to our views
        mCalcButton = mActivity.findViewById(R.id.btn_calc);
        mConvertedTextView = mActivity.findViewById(R.id.txt_converted);
        mAmountEditText = mActivity.findViewById(R.id.edt_amount);
        mForSpinner = mActivity.findViewById(R.id.spn_for);
        mHomSpinner = mActivity.findViewById(R.id.spn_hom);

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
