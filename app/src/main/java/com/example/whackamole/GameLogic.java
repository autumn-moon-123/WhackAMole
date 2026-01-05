package com.example.whackamole;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private Context context;
    private ArrayList<Mole> moles;
    private TextView scoreTextView;
    private TextView timerTextView;

    private int currentScore = 0;
    private boolean isGameRunning = false;

    // 游戏配置：地鼠每1秒换一次，总共30秒
    private final long MOLE_DISPLAY_TIME = 1000;
    private final long GAME_DURATION = 30000;

    private Handler moleHandler = new Handler();
    private Runnable moleRunnable;
    private CountDownTimer gameTimer;
    private Random random = new Random();
    private int currentMoleIndex = -1;

    // 构造函数：初始化所有的洞
    public GameLogic(Context context, ArrayList<ImageView> moleViews, TextView scoreText, TextView timerText) {
        this.context = context;
        this.scoreTextView = scoreText;
        this.timerTextView = timerText;
        this.moles = new ArrayList<>();

        // 遍历这9个图片，把它们变成 Mole 对象
        for (int i = 0; i < moleViews.size(); i++) {
            ImageView iv = moleViews.get(i);
            Mole mole = new Mole(i, iv);
            moles.add(mole);

            final int index = i;
            // 设置点击事件：如果你点到了地鼠
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 只有游戏在运行，且这个洞里有地鼠，才算分
                    if (isGameRunning && moles.get(index).isVisible()) {
                        currentScore++;
                        updateScoreText();
                        hideMole(); // 打中后马上消失
                    }
                }
            });
        }
    }

    // 开始游戏
    public void startGame() {
        currentScore = 0;
        updateScoreText();
        isGameRunning = true;

        startTimer();     // 启动倒计时
        startMoleLoop();  // 启动地鼠循环
    }

    // 倒计时逻辑
    private void startTimer() {
        gameTimer = new CountDownTimer(GAME_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Timer: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Timer: 0");
                endGame();
            }
        }.start();
    }

    // 地鼠循环出现逻辑 (参考 Appendix E)
    private void startMoleLoop() {
        moleRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isGameRunning) return;

                hideMole(); // 隐藏上一个

                // 随机选一个新洞，尽量不和上次一样
                int newIndex;
                do {
                    newIndex = random.nextInt(moles.size());
                } while (newIndex == currentMoleIndex);

                showMole(newIndex);

                // 安排下一次运行
                moleHandler.postDelayed(this, MOLE_DISPLAY_TIME);
            }
        };
        moleHandler.post(moleRunnable);
    }

    private void stopMoleLoop() {
        moleHandler.removeCallbacks(moleRunnable);
    }

    private void showMole(int index) {
        currentMoleIndex = index;
        moles.get(index).setVisible(true);
    }

    private void hideMole() {
        if (currentMoleIndex != -1) {
            moles.get(currentMoleIndex).setVisible(false);
        }
    }

    private void updateScoreText() {
        scoreTextView.setText("Score: " + currentScore);
    }

    // 游戏结束
    private void endGame() {
        isGameRunning = false;
        stopMoleLoop();
        hideMole();

        // 跳转到 PlayerActivity (输入名字界面)，并把分数传过去
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra("SCORE", currentScore);
        context.startActivity(intent);
    }
}