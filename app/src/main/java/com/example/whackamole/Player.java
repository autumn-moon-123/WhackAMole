package com.example.whackamole;

import android.graphics.drawable.Drawable;


public class Player implements Comparable<Player> {
    private String playerName;
    private Drawable playerAvatar;
    private int playerScore;


    public Player(String name, Drawable avatar, int score) {
        this.playerName = name;
        this.playerAvatar = avatar;
        this.playerScore = score;
    }

    // Getter 方法
    public String getPlayerName() { return playerName; }
    public Drawable getPlayerAvatar() { return playerAvatar; }
    public int getPlayerScore() { return playerScore; }


    @Override
    public int compareTo(Player other) {
        return Integer.compare(other.playerScore, this.playerScore);
    }
}