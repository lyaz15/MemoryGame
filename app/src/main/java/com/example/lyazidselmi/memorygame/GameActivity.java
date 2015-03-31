package com.example.lyazidselmi.memorygame;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by lyazid.selmi on 23/03/2015.
 */
public class GameActivity extends Activity{


    private GridView gameView;
    private CardFlipAdapter cardAdapter;
    private boolean win;
    private  CardFlipView[] cards;
    private int nbValidCards;
    private CardFlipView previousCard,currentCard;

    public GameActivity(){
        super();
        win = false;
        nbValidCards = 0;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        gameView = (GridView) findViewById(R.id.gameView1);
        cards = new CardFlipView[]{
                new CardFlipView(this,R.drawable.image1),
                new CardFlipView(this,R.drawable.image1),
                new CardFlipView(this,R.drawable.image2),
                new CardFlipView(this,R.drawable.image2),
                new CardFlipView(this,R.drawable.image3),
                new CardFlipView(this,R.drawable.image3)
        };
        cardAdapter = new CardFlipAdapter(cards);
        gameView.setAdapter(cardAdapter);
        //gameView.setAdapter(adapter);
        gameView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View v,int position, long id) {
                if(previousCard == null){
                    previousCard = (CardFlipView)v;
                    previousCard.flipView();
                }
                else
                {
                    currentCard = (CardFlipView)v;
                    if(currentCard.isShowingHeadCard())
                        return;
                    currentCard.flipView();
                }
                cardAdapter.notifyDataSetChanged();
            }
        });
    }
    void showDialog() {
        int title;
        if(win)
            title = R.string.alert_dialog_win_title;
        else
            title = R.string.alert_dialog_lose_title;
        DialogFragment newFragment = YesNoDialogFragment.newInstance(title);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void handleDialog(boolean restart){
        if(restart){
            this.reset();
        }else
            this.finish();
    }

    public void checkCards(){
        if(previousCard != null && currentCard != null){
            if(previousCard.getHeadCardId() != currentCard.getHeadCardId()){
                previousCard.flipView();
                currentCard.flipView();
                cardAdapter.notifyDataSetChanged();
            }
            else{
                previousCard.canFlip(false);
                currentCard.canFlip(false);
                if((nbValidCards +=2) == cards.length){
                    win = true;
                    showDialog();
                }
            }
            previousCard = null;
            currentCard = null;
        }
    }

    private void reset(){
        for(int i =0;i < cards.length;i++){
            cards[i].reset();
        }
        cardAdapter.notifyDataSetChanged();
        win = false;
        nbValidCards = 0;
        previousCard = null;
        currentCard = null;
    }
}