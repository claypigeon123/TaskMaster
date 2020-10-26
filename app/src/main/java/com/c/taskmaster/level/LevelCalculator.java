package com.c.taskmaster.level;

public class LevelCalculator {
    public static Integer calculateLevel(int exp) {
        return (int)(Math.sqrt(625 + 100 * exp) -25 ) / 50;
    }

    public static boolean hasLeveledUp(int prev, int current) {
        int prevLevel = calculateLevel(prev);
        int currentLevel = calculateLevel(current);

        return prevLevel < currentLevel;
    }

    public static int nextLevelAt(int level, int exp) {
        int targetLevel = level + 1;

        return 25 * targetLevel * (1 + targetLevel);
    }
}
