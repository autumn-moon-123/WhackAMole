package com.example.whackamole;

import android.widget.ImageView;

public class Mole {
    private int index;
    private ImageView imageView; // 对应界面上的图片控件
    private boolean isVisible;   // 地鼠是否钻出来了

    public Mole(int index, ImageView imageView) {
        this.index = index;
        this.imageView = imageView;
        this.isVisible = false;
    }

    // Getter 方法
    public int getIndex() { return index; }
    public ImageView getImageView() { return imageView; }
    public boolean isVisible() { return isVisible; }

    // 控制地鼠显示/隐藏的方法
    public void setVisible(boolean visible) {
        this.isVisible = visible;
        if (visible) {

            imageView.setImageResource(R.drawable.img_with_mole);
            imageView.setTag("MOLE"); // 标记它现在是地鼠
        } else {
            // 显示空洞
            imageView.setImageResource(R.drawable.img_without_mole);
            imageView.setTag("HOLE");
        }
    }
}