package com.example.batest39.myfirstapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;


public class MathAreaFragment extends Fragment {

    private int num1, num2;
    private char symbol;
    private ArrayList<String> listOfEquations;
    private Button submitButton;
    private TextView problemText;
    private TextView historyString;
    private EditText answerInput;

    public MathAreaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_math_area, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("listOfEquations", listOfEquations);
    }

    //treated as a constructor
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        this.num1 = 0;
        this.num2 = 0;
        this.symbol = ' ';
        this.listOfEquations = new ArrayList<String>();
        this.submitButton = getView().findViewById(R.id.submitButton);
        this.problemText = getView().findViewById(R.id.problemText);
        this.answerInput = getView().findViewById(R.id.answerInput);
        this.historyString = getView().findViewById(R.id.historyString);
        getMathProblem();
        getMathListener();
    }


    public void onResume(){
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public void getMathListener(){
        submitButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if (!answerInput.getText().toString().equals("")) {
                        int answerInt = Integer.parseInt(answerInput.getText().toString());
                        String equation = listOfEquations.get(listOfEquations.size() - 1);
                        int correctAnswer = getCorrectAnswer(equation);
                        equation = equation.replace("\n", correctAnswer + "\n");
                        //this if-statement is unnecessary right now but will likely be needed in
                        //the future
                        if(correctAnswer == answerInt){
                            historyString.setText(equation);
                            getMathProblem();
                            answerInput.setText("");
                        } else {
                            historyString.setText(equation);
                            getMathProblem();
                            answerInput.setText("");
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private int getCorrectAnswer(String equation){
        equation = equation.replace("\n", "");
        StringTokenizer st = new StringTokenizer(equation);
        String firstNum = st.nextToken();
        symbol = st.nextToken().charAt(0);
        String secondNum = st.nextToken();


        num1 = Integer.parseInt(firstNum);
        num2 = Integer.parseInt(secondNum);

        if(symbol == '+'){
            return num1 + num2;
        } else if(symbol == '-'){
            return num1 - num2;
        } else {
            return num1 * num2;
        }
    }


    private void getMathProblem(){
        Random random = new Random();

        String equation = "";
        int answer = -1;

        while(answer < 0){
            num1 = random.nextInt(16);
            symbol = getSymbol(random.nextInt(2));
            num2 = random.nextInt(16);
            equation = num1 + " " + symbol + " " + num2 + " =";
            answer = getCorrectAnswer(equation);
        }

        if(!equation.equals("")){
            listOfEquations.add(equation + "\n");
            problemText.setText(equation);
        }
    }

    private char getSymbol(int inNum){
        if(inNum == 0){
            return '+';
        } else if (inNum == 1){
            return '-';
        } else {
            return '*';
        }
    }
}
