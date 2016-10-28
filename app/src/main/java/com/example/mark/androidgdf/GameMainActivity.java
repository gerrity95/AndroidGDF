package com.example.mark.androidgdf;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameMainActivity extends AppCompatActivity {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;
    public static GameView sGame;
    public static AssetManager assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assets = getAssets();
        sGame = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
        setContentView(sGame); //Default is setContentView(R.layout.activity_game_main);

    }
}
