package com.example.mark.androidgdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

import com.example.mark.framework.util.InputHandler;
import com.example.mark.framework.util.Painter;
import com.example.mark.game.state.State;
import com.example.mark.game.state.LoadState;

/**
 * Created by Mark on 28/10/2016.
 */

public class GameView extends SurfaceView implements Runnable {

    private Bitmap gameImage;
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    private Painter graphics;

    private Thread gameThread;
    private volatile boolean running = false;
    private volatile State currentState;

    private InputHandler inputHandler;

    public GameView(Context context, int gameWidth, int gameHeight)
    {
        super(context);
        gameImage = Bitmap.createBitmap(gameWidth, gameHeight, Bitmap.Config.RGB_565); //This means it will cover the entire screen and not be transparent
        gameImageSrc = new Rect(0, 0, gameImage.getWidth(), gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage); //Draws images to the screen as requested by the current state
        graphics = new Painter(gameCanvas);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initInput();
                if (currentState == null) //This means if a user leaves the app and comes back they will be returned to same state, i.e. the currentState
                {
                    setCurrentState(new LoadState());
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                pauseGame();
            }
        });
    }

    public GameView(Context context)
    {
        super(context);
    }

    public void setCurrentState(State newState) {
        System.gc();
        newState.init();
        currentState = newState;
        inputHandler.setCurrentState(currentState);
    }

    private void initInput() {
        if (inputHandler == null)
        {
            inputHandler = new InputHandler();
        }
        setOnTouchListener(inputHandler);
    }

    private void initGame()
    {
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    private void pauseGame() {
        running = false;
        while (gameThread.isAlive())
        {
            try {
                gameThread.join();
                break;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public void run() {

    }
}