package com.example.whackamole;

import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {
    // 单例实例
    private static Leaderboard leaderboardInstance;
    private ArrayList<Player> leaderboard;
    private final int MAX_LEADERBOARD_SIZE = 5;


    private Leaderboard() {
        leaderboard = new ArrayList<>();
    }

    // 获取单例的方法
    public static Leaderboard getInstance() {
        if (leaderboardInstance == null) {
            leaderboardInstance = new Leaderboard();
        }
        return leaderboardInstance;
    }


    public void updateLeaderboard(Player newPlayer) {
        leaderboard.add(newPlayer);
        Collections.sort(leaderboard); // 自动排序（因为 Player 实现了 Comparable）


        if (leaderboard.size() > MAX_LEADERBOARD_SIZE) {
            leaderboard.remove(leaderboard.size() - 1);
        }
    }

    // 获取列表
    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }
}