package com.mahdishamspoour.animbutton;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class AnimButton extends MaterialButton {
    private ObjectAnimator onClickAnim,onLongClickAnim;
    private final long DURATION=130;
    private final float ON_CLICK_SCALE=0.97f;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public AnimButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupOnClickAnim();
        setupOnLongClickAnim();
        initComponents();
    }

    private void initComponents() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickAnim!=null){
                    onClickAnim.cancel();
                    onClickAnim.start();
                }
                if (onClickListener!=null){
                    onClickListener.onClick(v);
                }
            }
        });
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongClickAnim!=null){
                    onLongClickAnim.cancel();
                    onLongClickAnim.start();
                }
                if (onLongClickListener!=null){
                    onLongClickListener.onLongClick(v);
                }
                return true;
            }
        });
    }

    private void setupOnClickAnim(){
        onClickAnim = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat("scaleX", ON_CLICK_SCALE),
                PropertyValuesHolder.ofFloat("scaleY", ON_CLICK_SCALE));
        onClickAnim.setDuration(DURATION);
        setupCommonClickAnim(onClickAnim);
    }
    private void setupOnLongClickAnim(){
        onLongClickAnim = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat("scaleX", (float) (ON_CLICK_SCALE-0.06)),
                PropertyValuesHolder.ofFloat("scaleY", (float) (ON_CLICK_SCALE-0.06)));
        onLongClickAnim.setDuration(DURATION+90);
        setupCommonClickAnim(onLongClickAnim);
    }
    private void setupCommonClickAnim(ObjectAnimator objectAnimator){
        objectAnimator.setRepeatCount(ObjectAnimator.RESTART);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
    }

    public void setOnNewClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    public void setOnNewLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener=onLongClickListener;
    }

}