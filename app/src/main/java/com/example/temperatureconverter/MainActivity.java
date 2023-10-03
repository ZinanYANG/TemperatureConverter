package com.example.temperatureconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private TextView textView3;
    private TextView textView5;
    private TextView history;
    private List<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView3 = findViewById(R.id.textView3);
        textView5 = findViewById(R.id.textView5);
        history = findViewById(R.id.history);
        radioGroup = findViewById(R.id.radioGroup);
        EditText et2 = (EditText) findViewById(R.id.editTextNumber2);
    }

    public void FToC(View v) {

        int radioChecked = radioGroup.getCheckedRadioButtonId();
        textView3.setText(String.format("Fahrenheit Degrees:"));
        textView5.setText(String.format("Celsius Degrees:"));
    }

    public void CToF(View v) {

        int radioChecked = radioGroup.getCheckedRadioButtonId();
        textView3.setText("Celsius Degrees:");
        textView5.setText("Fahrenheit Degrees:");
    }

    public void doConvert(View v) {


        EditText et1 = (EditText) findViewById(R.id.editTextNumber);
        EditText et2 = (EditText) findViewById(R.id.editTextNumber2);

        String string1 = et1.getText().toString();

        int radioChecked = radioGroup.getCheckedRadioButtonId();
        String conversionString;
        if (radioChecked == R.id.radioButton3) {
            if (string1.trim().isEmpty()) {
                return;
            }
            double n1 = Double.parseDouble(string1);
            double result = (n1 - 32) / 1.8;
            et2.setText(String.format("%.1f", result));

//            each time user input the value and get the conversion
//            result gets stored into the history arraylist
            String convertedValue = String.format("%.1f", result);
            conversionString = string1 + " F ==>" + convertedValue + " C";


        } else {


            if (string1.trim().isEmpty()) {
                return;
            }
            double n1 = Double.parseDouble(string1);
            double result = (n1 * 1.8) + 32;
            et2.setText(String.format("%.1f", result));


//            each time user input the value and get the conversion
//            result gets stored into the history arraylist
            String convertedValue = String.format("%.1f", result);
            conversionString = string1 + " C ==>" + convertedValue + " F";
        }

//        add the first item to the array list
        historyList.add(0, conversionString);
//        Update conversionHistory TextView
        StringBuilder historyStringBuilder = new StringBuilder();

//        add new line to all conversion items
        for (String str : historyList) {
            historyStringBuilder.append(str).append("\n");
        }

        history.setText(historyStringBuilder.toString());

        et1.setText("");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putStringArrayList("historyList", new ArrayList<>(historyList));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        historyList = savedInstanceState.getStringArrayList("historyList");
        if (historyList == null) {
            historyList = new ArrayList<>();
        }
        ;
        StringBuilder historyStringBuilder = new StringBuilder();
        for (String str : historyList) {
            historyStringBuilder.append(str).append("\n");
        }
        history.setText(historyStringBuilder.toString());
    }

    public void clear(View v) {
//        clear history array
        historyList.clear();
        history.setText("");
        EditText et2 = (EditText) findViewById(R.id.editTextNumber2);
        et2.setText("");

    }


}