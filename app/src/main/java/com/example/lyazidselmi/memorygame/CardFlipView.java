package com.example.lyazidselmi.memorygame;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    public CardFlipView(Context context, int headCardId){
        super(context);
        isShowingHeadCard = false;
        this.headCardId = headCardId;
        canFlip = true;
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

    }

    @Override
    public void onAnimationEnd(Animation animation) {
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
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void canFlip(boolean canflip){
        this.canFlip = canflip;
    }
}
