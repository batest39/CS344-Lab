package com.example.batest39.myfirstapplication;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation==
                Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_landscape);
        } else{
            setContentView(R.layout.activity_portrait);
        }
        //this leaves the keyboard hidden on load
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void getRandomCards(){
        TypedArray cardImageArray = getResources().obtainTypedArray(R.array.cards);
        TypedArray cardsInHand = getResources().obtainTypedArray(R.array.cardsInHand);

        for (int i = 0; i < 3; i++){
            int rand = (int) (Math.random() * cardImageArray.length());
            ((ImageView)findViewById(cardsInHand.getResourceId(i, R.id.card1))).setImageResource(cardImageArray.getResourceId(rand, R.drawable.c1));
        }
    }
}
