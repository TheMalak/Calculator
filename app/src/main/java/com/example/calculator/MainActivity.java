package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView operationsViewText;
    TextView resultViewText;
    String operations = "";
    static final String SINTAXTERROR = "Error de Sintaxis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    //inicializa los valores de los campos de textView
    private void initTextViews() {
        operationsViewText = (TextView)findViewById(R.id.workingsTextView);
        resultViewText = (TextView)findViewById(R.id.resultsTextView);
        operationsViewText.setText("");
        resultViewText.setText("0");
    }

    //asigna los valores al ser presionados
    private void setOperations(String givenValue){
        operations = operations + givenValue;
        operationsViewText.setText(operations);
    }

    //remueve el ultimo caracter en la lista
    public void delOnClick(View view) {

        String currentVal = operationsViewText.getText().toString();
        String resultVal = resultViewText.getText().toString();

        if(resultVal == SINTAXTERROR) {
            resultViewText.setText("0");
        }

        if(!currentVal.isEmpty()) {

            String newValue = currentVal.substring(0, currentVal.length() -1);
            operationsViewText.setText(newValue);
            operations = newValue;
        }

    }


    public void equalsOnClick(View view){

        Expression expression = new Expression(addPercent());
        //Log.i("currentOperation", );
        double result = expression.calculate();
        if(String.valueOf(result) == "NaN")
            resultViewText.setText(SINTAXTERROR);
        else
            resultViewText.setText(String.valueOf(result));

    }

    private String addPercent()
    {

        char[] arr = operations.toCharArray();
        List<String> tempArrPercent = new ArrayList<String>();

        for (int i = 0; i < arr.length; i++){

            if(arr[i] == '%'){

                int data = arr[i];
                tempArrPercent.add("*(");

                for (int j = i+1; j < arr.length; j++) {

                    try {
                        int newNumberPercenten = Integer.parseInt(String.valueOf(arr[j]));
                        tempArrPercent.add(String.valueOf(newNumberPercenten));

                    } catch (Exception d) {
                        data = j;
                        break;
                    }

                }

                tempArrPercent.add("/100)");

                i = data - 1;

            }
            else {
                tempArrPercent.add(String.valueOf(arr[i]));
            }
        }

        return TextUtils.join("", tempArrPercent);

    }


    public void clearOnClick(View view){
        operationsViewText.setText("");
        operations = "";
        resultViewText.setText("0");
    }

    public void moduleOnClick(View view) {
        setOperations("%");
    }

    public void dividerOnClick(View view) {
        setOperations("/");
    }

    public void sevenOnClick(View view) {
        setOperations("7");
    }

    public void eightOnClick(View view) {
        setOperations("8");
    }

    public void nineOnClick(View view) {
        setOperations("9");
    }

    public void multiplicationOnClick(View view) {
        setOperations("*");
    }

    public void fourOnClick(View view) {
        setOperations("4");
    }

    public void fiveOnClick(View view) {
        setOperations("5");
    }

    public void sixOnClick(View view) {
        setOperations("6");
    }

    public void restOnClick(View view) {
        setOperations("-");
    }

    public void oneOnClick(View view) {
        setOperations("1");
    }

    public void twoOnClick(View view) {
        setOperations("2");
    }

    public void threeOnClick(View view) {
        setOperations("3");
    }

    public void additionOnClick(View view) {
        setOperations("+");
    }

    public void zeroOnClick(View view) {
        setOperations("0");
    }

    public void dotOnClick(View view) {
        setOperations(".");
    }

    public void parentesisLeftOnClick(View view) {
        setOperations("(");
    }

    public void parentesisRightOnClick(View view) {
        setOperations(")");
    }

    public void powOnClick(View view) {
        setOperations("^");
    }

    public void sinOnClick(View view) {
        setOperations("sin(");
    }

    public void tanhOnClick(View view) {
        setOperations("tan(");
    }

    public void coshOnClick(View view) {
        setOperations("cosh(");
    }

    public void sinhOnClick(View view) {
        setOperations("sinh(");
    }

    public void tanOnClick(View view) {
        setOperations("tan(");
    }

    public void cosOnClick(View view) {
        setOperations("cos(");
    }

    public void lnOnClick(View view) {
        setOperations("ln(");
    }

    public void squareRootonClick(View view) {
        setOperations("√");
    }

    public void tenPowOnClick(View view) {
        setOperations("10^");
    }

    public void powOnClickE(View view) {
        setOperations("e^");
    }

    public void piOnClick(View view) {
        setOperations("π");
    }

    public void log10OnClick(View view) {
        setOperations("log10(");
    }
}