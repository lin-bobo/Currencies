package com.apress.gerber.currencies;

import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void testInteger() throws Throwable {
        proxyCurrencyConverterTask("12");
    }

    @Test
    public void testFloat() throws Throwable {
        proxyCurrencyConverterTask("12.3");
    }

    public void proxyCurrencyConverterTask(final String str) throws Throwable{

        final CountDownLatch latch = new CountDownLatch(1);

        mActivity.setCurrencyTaskCallback(new MainActivity.CurrencyTaskCallback() {
            @Override
            public void executionDone() {
                latch.countDown();
                assertEquals(convertToDouble(mConvertedTextView.getText().toString().substring(0, 5)), convertToDouble(str));
            }
        });
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAmountEditText.setText(str);
                mForSpinner.setSelection(0);
                mHomSpinner.setSelection(0);
                mCalcButton.performClick();
            }
        });

        latch.await(30, TimeUnit.SECONDS);
    }

    private double convertToDouble(String str) throws NumberFormatException{
        double dReturn = 0;
        try {
            dReturn = Double.parseDouble(str);
        }catch (NumberFormatException e){
            throw e;
        }
        return dReturn;
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}

