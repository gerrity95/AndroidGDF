package com.example.mark.framework.util;

import android.view.MotionEvent;
import android.view.View;

import com.example.mark.game.state.State;
import com.example.mark.androidgdf.GameMainActivity;

/**
 * Created by gerrity95 on 29/10/16.
 */
public class InputHandler implements View.OnTouchListener {

    private State currentState;

    public void setCurrentState(State currentState)
    {
        this.currentState = currentState;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int scaledX = (int) ((event.getX() / v.getWidth()) * GameMainActivity.GAME_WIDTH);
        int scaledY = (int) ((event.getY() / v.getHeight()) * GameMainActivity.GAME_HEIGHT);

        return currentState.onTouch(event,scaledX, scaledY);
    }
}
