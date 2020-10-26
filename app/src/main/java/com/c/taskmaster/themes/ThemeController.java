package com.c.taskmaster.themes;

import com.c.taskmaster.R;

public class ThemeController {
    public static int getCurrentTheme(int code) {
        switch (code) {
            case 0:
                return R.style.AppTheme;
            case 1:
                return R.style.AppTheme2;
            case 2:
                return R.style.AppTheme3;
            case 3:
                return R.style.AppTheme4;
        }

        return R.style.AppTheme;
    }
}
