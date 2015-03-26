package com.example.lyazidselmi.memorygame;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.view.View;

import java.util.Objects;

/**
 * Created by lyazid.selmi on 23/03/2015.
 */
public class GameActivity extends Activity{

    private GridView gameView;
    private final ImageAdapter adapter;
    private Integer firstClick, secondClick;
    private final static int END_GAME = 10;
    private boolean restart;
    private String dialog_message;
    private Integer[] cardIds;
    static  final Integer[] tails = new Integer[]{
            R.drawable.back
    };
    static final Integer[] heads = new Integer[]{
            R.drawable.image1,
            R.drawable.image2
    };

    private CardFlipView[] cardViews;

    public GameActivity(){
        super();
        cardIds = new Integer[] {
                R.drawable.back,
                R.drawable.back,
                R.drawable.back,
                R.drawable.back
        };
        restart = false;
        firstClick = -1;
        secondClick = -1;
        adapter = new ImageAdapter(this,cardIds);
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        gameView = (GridView) findViewById(R.id.gameView1);

        gameView.setAdapter(adapter);
        gameView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ((CardFlipView)adapter.getView(position,null,gameView)).flipView();
                adapter.notifyDataSetChanged();
                gameView.setAdapter(adapter);
                gameView.invalidateViews();
                /**
                if(position < 2)
                    cardIds[position] = heads[0];
                else
                    cardIds[position] = heads[1];

                if(firstClick == -1){
                    firstClick = position;
                }
                else {
                    secondClick = position;
                    if((firstClick < 2 && secondClick < 2) || (firstClick >= 2 && secondClick >= 2))
                        dialog_message = "Congratulations you win :)";
                        // Toast.makeText(getApplicationContext(),
                        //       "You win", Toast.LENGTH_LONG).show();
                    else
                        dialog_message = "OOOPS you lose !";
                    //Toast.makeText(getApplicationContext(),
                    //      "You lose", Toast.LENGTH_SHORT).show();
                    dialog_message += " \nTry again ?";
                    showDialog(END_GAME);

                }
                Log.i("Memory Game","here");
                adapter.notifyDataSetChanged();
                gameView.setAdapter(adapter);
                gameView.invalidateViews();**/
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id) {
            case END_GAME:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(dialog_message);
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new OkOnClickListener());
                builder.setNegativeButton("No", new CancelOnClickListener());
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);
    }

    private void handleDialog(){
        if(restart){
            cardIds[firstClick] = tails[0];
            cardIds[secondClick] = tails[0];
            firstClick = -1;
            secondClick = -1;
            restart = false;
            adapter.notifyDataSetChanged();
            gameView.setAdapter(adapter);
            gameView.invalidateViews();
        }else
            this.finish();
    }
    private final class CancelOnClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            restart = false;
            handleDialog();
        }
    }

    private final class OkOnClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            restart = true;
            handleDialog();
        }
    }
}