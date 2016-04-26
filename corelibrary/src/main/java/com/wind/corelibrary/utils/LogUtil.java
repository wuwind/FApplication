package com.wind.corelibrary.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/3/11.
 */
public class LogUtil {

    public static boolean isDbug = true;
    public static int debugIDs[] = new int[]{0,1,2, 3};//

    public static void e(int id, double content) {
        e(id,"", content+"");
    }

    public static void e(int id,  String Tag, double content) {
        e(id, Tag, content+"");
    }

    public static void e(int id, String content) {
        e(id, "LogUtil", content);
    }

    public static void e(int id, String Tag, String content) {
        if(isDbug) {
            for (int debugID : debugIDs) {
                if(debugID == id) {
                    Log.e(Tag + " debugId:" + id, content);
                    break;
                }
            }
        }
    }
}
