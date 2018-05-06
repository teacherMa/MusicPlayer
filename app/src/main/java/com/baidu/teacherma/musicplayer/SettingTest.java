package com.baidu.teacherma.musicplayer;

/**
 * Created by teacherMa on 2018/5/5
 */
public class SettingTest {

    private int mNumber;
    private static int sNum;

    public static int getNum() {
        return sNum;
    }

    public static void setNum(int num) {
        sNum = num;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }
}
