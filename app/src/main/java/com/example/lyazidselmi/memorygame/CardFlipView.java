package com.example.lyazidselmi.memorygame;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by lyazid.selmi on 25/03/2015.
 */
public class CardFlipView extends ImageView implements Animation.AnimationListener {

    private boolean isShowingHeadCard;
    private Animation to_middle;
    private Animation from_middle;
    private boolean canFlip;
    private int headCardId;
    private String log;
    private boolean animStarted;
    static int count = 0;
    private int anim = 0;
    public CardFlipView(Context context, int headCardId){
        super(context);
        isShowingHeadCard = false;
        this.headCardId = headCardId;
        canFlip = true;
        this.setImageResource(R.drawable.back);
        this.setLayoutParams(new GridView.LayoutParams(-10, -10));
        this.setScaleType(ImageView.ScaleType.CENTER_CROP);
        to_middle = AnimationUtils.loadAnimation(context,R.anim.to_middle);
        to_middle.setAnimationListener(this);
        from_middle = AnimationUtils.loadAnimation(context,R.anim.from_middle);
        from_middle.setAnimationListener(this);
    }

    public void flipView(){
        if(!canFlip)
            return;
        this.clearAnimation();
        this.setAnimation(to_middle);
        this.startAnimation(to_middle);
    }



    @Override
    public void onAnimationStart(Animation animation) {
        animStarted = true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(!animStarted)
            return;
        animStarted = false;
        if (animation==to_middle) {
            if (isShowingHeadCard) {
                this.setImageResource(R.drawable.back);

            } else {
                this.setImageResource(headCardId);
            }
            this.clearAnimation();
            this.setAnimation(from_middle);
            this.startAnimation(from_middle);
        } else {
            isShowingHeadCard=!isShowingHeadCard;
        }
        count++;
        if(count == 4){
            ((GameActivity)getContext()).checkCards();
            count = 0;
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public boolean isShowingHeadCard(){
        return isShowingHeadCard;
    }
    public void canFlip(boolean canflip){
        this.canFlip = canflip;
    }

    public int getHeadCardId(){
        return headCardId;
    }

    public void reset(){
        if(!isShowingHeadCard())
            return;
        this.setImageResource(R.drawable.back);
        isShowingHeadCard = false;
        canFlip = true;
    }
}
