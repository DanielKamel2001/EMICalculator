package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //    The EMI reducing-balance method is calculated using this formula:
//    EMI = P * [( r * (1 + r)^n)) / ((1 + r)^n - 1)]
//    where:
//    P = Princiapl amount borrowed
//    r = Periodic monthly interest rate
//    n = Total number of monthly payments
    public double EMIcalc(float principal, float rate, int monthlyPayments) {
        return principal * ((rate * Math.pow((1 + rate), monthlyPayments)) / (Math.pow((1 + rate), monthlyPayments) - 1));
    }

    public void CalculateClick(View v) {

        //get result TextView for writing messages
        TextView calcResult = (TextView) findViewById(R.id.calcResult);

        //store string of values to confirm they are not null
        String principalAmountString, rateAmountString;
        principalAmountString = String.valueOf(((EditText) findViewById(R.id.principalAmountTextbox)).getText());
        rateAmountString = String.valueOf(((EditText) findViewById(R.id.interestRateTextBox)).getText());
        if (principalAmountString.isEmpty() || rateAmountString.isEmpty()) {
            calcResult.setText("Please enter all needed values.");
            return;
        }

        //calculate Values
        float principalAmount = Float.parseFloat(principalAmountString);
        float rateAmount = Float.parseFloat(rateAmountString);
        rateAmount = rateAmount/100;

        int yearAmount = Integer.parseInt((((Spinner) findViewById(R.id.amortizationYearsDropdown)).getSelectedItem().toString()).split(" ")[0]);
        int monthAmount = Integer.parseInt((((Spinner) findViewById(R.id.amortizationMonthDropdown)).getSelectedItem().toString()).split(" ")[0]);
        int totalMonthAmount = (yearAmount * 12) + monthAmount;

        //log for testing
        System.out.println(principalAmount  + " "+ totalMonthAmount + " "+rateAmount);

        //Calculate Value and set the message
        double EmiResult = EMIcalc(principalAmount, rateAmount, totalMonthAmount);
        calcResult.setText(String.format("You owe: %.2f",  EmiResult));
        //    calcResult.setText("Hello World " + counter );
        //    counter++;
    }
}