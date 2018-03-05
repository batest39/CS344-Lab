package com.example.batest39.myfirstapplication;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView card1;
    private ImageView card2;
    private ImageView card3;
    private ImageView display1;
    private ImageView display2;
    private ImageView warCard1;
    private ImageView warCard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation==
                Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_landscape);
            setCards();
            getListeners();
            getRandomCards(card1, card2, card3);
        } else{
            setContentView(R.layout.activity_portrait);
            setCards();
            getListeners();
            getRandomCards(card1, card2, card3);
        }
        //this leaves the keyboard hidden on load
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void setCards(){
        this.card1 = findViewById(R.id.card1);
        this.card2 = findViewById(R.id.card2);
        this.card3 = findViewById(R.id.card3);

        this.display1 = findViewById(R.id.display1);
        this.display1.setImageDrawable(null);

        this.display2 = findViewById(R.id.display2);
        this.display2.setImageDrawable(null);

        this.warCard1 = findViewById(R.id.warCard1);
        this.warCard1.setImageDrawable(null);

        this.warCard2 = findViewById(R.id.warCard2);
        this.warCard2.setImageDrawable(null);
    }

    private void getRandomCards(ImageView card1, ImageView card2, ImageView card3){
        TypedArray cardImageArray = getResources().obtainTypedArray(R.array.cards);
        ImageView[] cardsInHand = new ImageView[3];

        cardsInHand[0] = card1;
        cardsInHand[1] = card2;
        cardsInHand[2] = card3;


        for (int i = 0; i < 3; i++){
            int rand = (int) (Math.random() * cardImageArray.length());
            cardsInHand[i].setImageResource(cardImageArray.getResourceId(rand, -1));
        }
    }


    public void getListeners(){
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == card1.getId()){
                    if(display1.getDrawable() != null){
                        display1.setImageDrawable(card1.getDrawable());
                    }
                    else{
                        display2.setImageDrawable(card1.getDrawable());
                    }
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == card2.getId()){
                    if(display1.getDrawable() != null){
                        display1.setImageDrawable(card2.getDrawable());
                    }
                    else{
                        display2.setImageDrawable(card2.getDrawable());
                    }
                }
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == card3.getId()){
                    if(display1.getDrawable() != null){
                        display1.setImageDrawable(card3.getDrawable());
                    }
                    else{
                        display2.setImageDrawable(card3.getDrawable());
                    }
                }
            }
        });
    }
}
