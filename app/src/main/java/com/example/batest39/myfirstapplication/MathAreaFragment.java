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


public class MathAreaFragment extends Fragment {

    private int num1, num2;
    private char symbol;
    private List<String> listOfEquations;
    private Button submitButton;
    private TextView problemText;
    private TextView historyString;
    private EditText answerInput;
    //private OnFragmentInteractionListener mListener;

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

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
                if (!answerInput.getText().toString().equals("")) {
                    return true;
                }
                return false;
            }
        });
    }


    private void getMathProblem(){

    }
}
