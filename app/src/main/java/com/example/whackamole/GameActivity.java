package com.example.whackamole;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // 1. 找到显示分数和时间的文本框
        TextView scoreText = findViewById(R.id.tv_score_text);
        TextView timerText = findViewById(R.id.tv_timer_text);

        // 2. 把 9 个 ImageView 放入列表，方便管理
        ArrayList<ImageView> moleViews = new ArrayList<>();
        moleViews.add(findViewById(R.id.iv_mole_01));
        moleViews.add(findViewById(R.id.iv_mole_02));
        moleViews.add(findViewById(R.id.iv_mole_03));
        moleViews.add(findViewById(R.id.iv_mole_04));
        moleViews.add(findViewById(R.id.iv_mole_05));
        moleViews.add(findViewById(R.id.iv_mole_06));
        moleViews.add(findViewById(R.id.iv_mole_07));
        moleViews.add(findViewById(R.id.iv_mole_08));
        moleViews.add(findViewById(R.id.iv_mole_09));

        // 3. 初始化游戏逻辑并开始游戏
        gameLogic = new GameLogic(this, moleViews, scoreText, timerText);
        gameLogic.startGame();
    }
}