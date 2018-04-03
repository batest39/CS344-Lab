package com.example.batest39.myfirstapplication;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView card1, card2, card3;
    private ImageView display1, display2;
    //private ImageView warCard1, warCard2;
    //private Button warButton;
    private FragmentManager frag;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation==
                Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_landscape);
            setCards();
            //this.warButton = findViewById(R.id.warButton);
            //warButton.setEnabled(false);
            Button chatButton = findViewById(R.id.chatButton);
            chatButton.setEnabled(true);
            EditText chatTextBox = findViewById(R.id.inputText);
            chatTextBox.setText("");
            TextView sentMessage = findViewById(R.id.chatMessage);
            sentMessage.setText("");
            getChatListeners(chatButton, chatTextBox, sentMessage);
            getListeners();
            getRandomCards(card1, card2, card3);
        } else{
            setContentView(R.layout.activity_portrait);
            setCards();
            //this.warButton = findViewById(R.id.warButton);
            //warButton.setEnabled(false);
            getListeners();
            getRandomCards(card1, card2, card3);
        }
        this.frag = getSupportFragmentManager();
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

        //this.warCard1 = findViewById(R.id.warCard1);
        //this.warCard1.setImageDrawable(null);

        //this.warCard2 = findViewById(R.id.warCard2);
        //this.warCard2.setImageDrawable(null);
    }

    private void getRandomCards(ImageView card1, ImageView card2, ImageView card3){
        TypedArray cardImageArray = getResources().obtainTypedArray(R.array.cards);
        ImageView[] cardsInHand = new ImageView[3];
        int[] randomCards = new int[3];

        cardsInHand[0] = card1;
        cardsInHand[1] = card2;
        cardsInHand[2] = card3;


        for (int i = 0; i < 3; i++){
            int rand = (int) (Math.random() * cardImageArray.length());
            randomCards[i] = cardImageArray.getResourceId(rand, -1);
            while (i > 0 && randomCards[i - 1] == randomCards[i]){ //eliminates duplicates
                rand = (int) (Math.random() * cardImageArray.length());
                randomCards[i] = cardImageArray.getResourceId(rand, -1);
            }
        }

        cardsInHand[0].setImageResource(randomCards[0]);
        cardsInHand[1].setImageResource(randomCards[1]);
        cardsInHand[2].setImageResource(randomCards[2]);
    }


    public void getListeners(){
        //Card listeners
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

    public void getChatListeners(Button inChatButton, EditText inChatTextBox, TextView inSentMessage){
        final Button chatButton = inChatButton;
        final EditText chatTextBox = inChatTextBox;
        final TextView sentMessage = inSentMessage;
        //Chat listeners
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEquap = !(chatTextBox.getText() + "").equals("");
                if(view.getId() == chatButton.getId() && !(chatTextBox.getText() + "").equals("")){
                    String chatText = chatTextBox.getText().toString().toLowerCase();
                    boolean equal = chatTextBox.getText().toString().toLowerCase().equals("math");
                    if(chatTextBox.getText().toString().toLowerCase().equals("math")){
                        FragmentTransaction fragTrans = frag.beginTransaction();
                        fragTrans.replace(R.id.globalContainer, new MathAreaFragment());
                        fragTrans.commit();
                    } else {
                        sentMessage.setText(chatTextBox.getText());
                        chatTextBox.setText("");
                    }
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
