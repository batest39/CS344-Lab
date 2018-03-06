package com.example.batest39.myfirstapplication;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private ImageView card1;
    private ImageView card2;
    private ImageView card3;
    private ImageView display1;
    private ImageView display2;
    private ImageView warCard1;
    private ImageView warCard2;
    private Button warButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation==
                Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_landscape);
            setCards();
            this.warButton = findViewById(R.id.warButton);
            warButton.setEnabled(false);
            getListeners();
            getRandomCards(card1, card2, card3);
        } else{
            setContentView(R.layout.activity_portrait);
            setCards();
            this.warButton = findViewById(R.id.warButton);
            warButton.setEnabled(false);
            getListeners();
            getRandomCards(card1, card2, card3);
        }
        //this leaves the keyboard hidden on load
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //Initializes the cards and hides all of the ones that aren't in the players hand
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
                    getConfirmDialog(view, card1);
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == card2.getId()){
                    getConfirmDialog(view, card2);
                }
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == card3.getId()){
                    getConfirmDialog(view, card3);
                }
            }
        });
    }

    private void getConfirmDialog(View view, ImageView inCard){
        final ImageView cardClicked = inCard;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        if(display1.getDrawable() == null){
                            display1.setImageDrawable(cardClicked.getDrawable());
                        }
                        else{
                            display2.setImageDrawable(cardClicked.getDrawable());
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are you sure you want to play this card?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}
