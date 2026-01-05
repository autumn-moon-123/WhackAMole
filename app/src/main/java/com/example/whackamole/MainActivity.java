package com.example.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 点击 "Play Game" 跳转到游戏页
    public void onClickPlay(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    // 点击 "Leaderboard" 跳转到排行榜页
    public void onClickLeaderboard(View view) {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
}