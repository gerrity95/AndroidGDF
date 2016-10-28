package com.example.mark.androidgdf;

import android.content.Context;
import android.view.SurfaceView;

import com.example.mark.game.state.State;

/**
 * Created by Mark on 28/10/2016.
 */

public class GameView extends SurfaceView {

    public GameView(Context context, int gameWidth, int gameHeight)
    {
        super(context);
    }

    public GameView(Context context)
    {
        super(context);
    }

    public void setCurrentState(State newState) {
    }
}