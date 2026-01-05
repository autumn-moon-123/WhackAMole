package com.example.whackamole;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PlayerActivity extends AppCompatActivity {

    private int finalScore;
    private EditText etName;
    private RadioGroup rgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // 1. 获取上一页传来的分数
        finalScore = getIntent().getIntExtra("SCORE", 0);

        // 2. 显示分数
        TextView tvScore = findViewById(R.id.tv_playerscore);
        tvScore.setText("Score: " + finalScore);

        etName = findViewById(R.id.et_playername);
        rgAvatar = findViewById(R.id.rg_avatar);
    }

    // 点击 Submit 按钮
    public void onclickSubmit(View view) {
        // 检查名字是否为空
        String name = etName.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        // 检查是否选了头像
        int selectedId = rgAvatar.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an avatar color", Toast.LENGTH_SHORT).show();
            return;
        }

        // 根据选择设置头像图片
        // 注意：这里假设你有这些颜色的地鼠图，如果没有，就全部用默认图
        Drawable avatarDrawable;
        if (selectedId == R.id.rb_grey) {
            avatarDrawable = ContextCompat.getDrawable(this, R.drawable.img_grey_mole);
        } else if (selectedId == R.id.rb_blue) {
            avatarDrawable = ContextCompat.getDrawable(this, R.drawable.img_blue_mole);
        } else if (selectedId == R.id.rb_orange) {
            avatarDrawable = ContextCompat.getDrawable(this, R.drawable.img_orange_mole);
        } else if (selectedId == R.id.rb_green) {
            avatarDrawable = ContextCompat.getDrawable(this, R.drawable.img_green_mole);
        } else {
            // Purple 和 Pink 默认用橙色或者普通地鼠图代替，防止报错
            avatarDrawable = ContextCompat.getDrawable(this, R.drawable.img_with_mole);
        }

        // 3. 创建玩家对象并存入排行榜
        Player player = new Player(name, avatarDrawable, finalScore);
        Leaderboard.getInstance().updateLeaderboard(player);

        // 4. 跳转到排行榜页面
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
}