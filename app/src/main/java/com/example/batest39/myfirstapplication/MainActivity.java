package com.example.batest39.myfirstapplication;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            getRandomCards(card1, card2, card3);
        } else{
            setContentView(R.layout.activity_portrait);
            setCards();
            getRandomCards(card1, card2, card3);
        }
        //this leaves the keyboard hidden on load
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void setCards(){
        this.card1 = (ImageView) findViewById(R.id.card1);
        this.card2 = (ImageView) findViewById(R.id.card2);
        this.card3 = (ImageView) findViewById(R.id.card3);

        this.display1 = (ImageView) findViewById(R.id.display1);
        this.display1.setImageDrawable(null);

        this.display2 = (ImageView) findViewById(R.id.display2);
        this.display2.setImageDrawable(null);

        this.warCard1 = (ImageView) findViewById(R.id.warCard1);
        this.warCard1.setImageDrawable(null);

        this.warCard2 = (ImageView) findViewById(R.id.warCard2);
        this.warCard2.setImageDrawable(null);
    }

    private void getRandomCards(ImageView card1, ImageView card2, ImageView card3){
        TypedArray cardImageArray = getResources().obtainTypedArray(R.array.cards);
        //TypedArray cardsInHand = getResources().obtainTypedArray(R.array.cardsInHand);
        ImageView[] cardsInHand = new ImageView[3];

        cardsInHand[0] = card1;
        cardsInHand[1] = card2;
        cardsInHand[2] = card3;


        for (int i = 0; i < 3; i++){
            int rand = (int) (Math.random() * cardImageArray.length());
            //((ImageView)findViewById(cardsInHand.getResourceId(i, R.id.card1))).setImageResource(cardImageArray.getResourceId(rand, R.drawable.c1));
            cardsInHand[i].setImageResource(cardImageArray.getResourceId(rand, R.drawable.c1));
        }
    }

/*    public void onTouch(View view){
        if (view == findViewById(this.card1.getId())){

        }
    }*/
}
