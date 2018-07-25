package com.apress.gerber.currencies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //define members that correspond to Views in our layout
    private Button mCalcButton;
    private TextView mConvertedTextView;
    private EditText mAmountEditText;
    private Spinner mForSpinner, mHomSpinner;
    private String[] mCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //unpack ArrayList from the bundle and convert to array
        ArrayList<String> arrayList = ((ArrayList<String>)
                getIntent().getSerializableExtra(SplashActivity.KEY_ARRAYLIST));
        Collections.sort(arrayList);
        mCurrencies = arrayList.toArray(new String[arrayList.size()]);

        //assign references to our Views
        mConvertedTextView = findViewById(R.id.txt_converted);
        mAmountEditText = findViewById(R.id.edt_amount);
        mCalcButton = findViewById(R.id.btn_calc);
        mForSpinner = findViewById(R.id.spn_for);
        mHomSpinner = findViewById(R.id.spn_hom);

        //controller: mediates model and view
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                //context
                this,
                //view: layout you see when the spinner is closed
                R.layout.spinner_closed,
                //model: the array of Strings
                mCurrencies
        );

        //view: layout you see when the spinner is open
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //assign adapters to spinners
        mHomSpinner.setAdapter(arrayAdapter);
        mForSpinner.setAdapter(arrayAdapter);

        mHomSpinner.setOnItemSelectedListener(this);
        mForSpinner.setOnItemSelectedListener(this);

    }

    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }
    private void launchBrowser(String strUri){

        if(isOnline()){
            Uri uri = Uri.parse(strUri);
            //call an implicit intent
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
    private void invertCurrencies(){
        int nFor = mForSpinner.getSelectedItemPosition();
        int nHom = mHomSpinner.getSelectedItemPosition();

        mForSpinner.setSelection(nHom);
        mHomSpinner.setSelection(nFor);

        mConvertedTextView.setText("");
    }

    private int findPositionGivenCode(String code, String[] currencies){

        for(int i = 0; i < currencies.length; i++){
            if((currencies[i]).substring(0, 3).equalsIgnoreCase(code)){
                return i;
            }
        }
        //default
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){

            case R.id.mnu_invert:
                invertCurrencies();
                break;
            case R.id.mnu_codes:
                launchBrowser(SplashActivity.URL_CODES);
                break;
            case R.id.mnu_exit:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){

            case R.id.spn_for:
                //define behavior here
                break;

            case R.id.spn_hom:
                //define behavior here
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
