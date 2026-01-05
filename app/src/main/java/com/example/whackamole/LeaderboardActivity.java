package com.example.whackamole;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // 1. 获取排行榜数据
        ArrayList<Player> topPlayers = Leaderboard.getInstance().getLeaderboard();

        // 2. 定义 XML 里 5 行的 ID
        int[] rowIds = {R.id.row_1, R.id.row_2, R.id.row_3, R.id.row_4, R.id.row_5};

        // 3. 循环填充数据
        for (int i = 0; i < rowIds.length; i++) {
            View row = findViewById(rowIds[i]);

            // 找到行内的控件
            ImageView ivAvatar = row.findViewById(R.id.iv_leaderboard_avatar);
            TextView tvName = row.findViewById(R.id.tv_leaderboard_name);
            TextView tvScore = row.findViewById(R.id.tv_leaderboard_score);

            if (i < topPlayers.size()) {
                // 如果这个位置有玩家数据，就显示出来
                Player p = topPlayers.get(i);
                ivAvatar.setImageDrawable(p.getPlayerAvatar());
                tvName.setText(p.getPlayerName() + " scored:");
                tvScore.setText(String.valueOf(p.getPlayerScore()));
                row.setVisibility(View.VISIBLE);
            } else {
                // 如果没数据（比如只有2个玩家，那第345行隐藏）
                row.setVisibility(View.INVISIBLE);
            }
        }
    }
}