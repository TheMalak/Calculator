package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView operationsViewText;
    TextView resultViewText;
    String operations = "";
    static final String SINTAXTERROR = "Error de Sintaxis";

    //atributtes
    //a = sin

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

    private void complexSolver(String valueToSolve) {

        char[] operationsToChar = operations.toCharArray(); //separamos cada uno de los valores actuales
        List<String> tempArrPercent = new ArrayList<String>();

        for (int i = 0; i < operationsToChar.length; i++){
            if(!isNumeric(operationsToChar[i])) { //cuando encuentre un elemento que no es un numero

            }
        }

//        double[] values = getTwoValuesInMiddleSign(operationsToChar, '^');
//        Log.i("pow", powSolver(values[0], values[1]));

    }

    private double[] getTwoValuesInMiddleSign(char[] operationsToChar, char symbol){

        List<String> firstNumber = new ArrayList<String>();
        List<String> secondNumber = new ArrayList<String>();

        for (int i = 0; i < operationsToChar.length; i++){
            if(!isNumeric(operationsToChar[i])) { //cuando encuentre un elemento que no es un numero
                //Log.i("number", String.valueOf(operationsToChar[i])); //comprobar que simbolo es
                char currentSymbol = operationsToChar[i];

                if (currentSymbol == symbol) {

                    //reversa
                    for (int j = i - 1; j >= 0; j--) {
                        if(isNumeric(operationsToChar[j])){
                            firstNumber.add(0, String.valueOf(operationsToChar[j]));
                        } else {
                            break;
                        }
                    }

                    //adelante
                    for (int k = i + 1; k < operationsToChar.length; k++) {
                        if(isNumeric(operationsToChar[k])){
                            secondNumber.add(String.valueOf(operationsToChar[k]));
                        } else {
                            break;
                        }
                    }

                }
            }
        }

        //toString
        double firstNumberDouble = Double.parseDouble(TextUtils.join("", firstNumber));
        double secondNumberDouble = Double.parseDouble(TextUtils.join("", secondNumber));

        double[] returnArray = {firstNumberDouble, secondNumberDouble};

        return returnArray;
    }

    private String powSolver(double a, double b){
        return String.valueOf("(" + Math.pow(a,b) + ")");
    }

    private String sinSolver(double a){
        return String.valueOf("*(" + Math.sin(a) + ")");
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }

    private boolean basicOperators(char c) {
        if(c == '+' || c == '-' || c == '*' || c == '/')
            return true;

        return false;
    }

    private void generateResult(){
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        String currentVal = operationsViewText.getText().toString();

        if(currentVal.isEmpty()) {

            return; //no hacer nada si no hay un resultado

        } else {
            try {
                result = (double)engine.eval("5*(5)");
            }
            catch (ScriptException e)
            { resultViewText.setText(SINTAXTERROR); }
            if(result != null) {

                String setResult = String.valueOf(result.doubleValue());
                if(setResult == "Infinity") {
                    setResult = SINTAXTERROR;
                } else {
                    DecimalFormat df = new DecimalFormat("#.00");
                    setResult = String.valueOf(Double.valueOf(df.format(result)));
                }
                resultViewText.setText(setResult);
            }
        }

    }

    //genera el resultado a traves de la libreria rhino
    public void equalsOnClick(View view)
    {
        //complexSolver(operationsViewText.getText().toString());
        generateResult();

        //Log.i("eval", String.valueOf(eval("ln5+5(sin7)")));

    }


    //remueve toda la informacion que contenga la vista
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
        setOperations("a");
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) x = Math.log10(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

}