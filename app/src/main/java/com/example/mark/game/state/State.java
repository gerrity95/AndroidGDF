package com.example.mark.game.state;

import android.view.MotionEvent;

import com.example.mark.framework.util.Painter;
import com.example.mark.androidgdf.GameMainActivity;
/**
 * Created by Mark on 28/10/2016.
 */

public abstract class State {

    public void setCurrentState(State newState)
    {
        GameMainActivity.sGame.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);
}