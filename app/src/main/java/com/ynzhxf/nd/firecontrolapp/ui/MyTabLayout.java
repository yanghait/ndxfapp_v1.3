package com.ynzhxf.nd.firecontrolapp.ui;

import android.content.Context;
//import com.google.android.material.tabs.TabLayout;
import android.util.AttributeSet;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

public class MyTabLayout extends TabLayout {

    public static int TabViewNumber = 2;

    public static final String SCROLLABLE_TAB_MIN_WIDTH = "mScrollableTabMinWidth";

    public MyTabLayout(Context context) {
        super(context);
        initTabMinWidth();
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTabMinWidth();
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTabMinWidth();
    }

    public void initTabMinWidth() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int tabMinWidth = screenWidth / TabViewNumber;
        Field field;
        try {
            field = TabLayout.class.getDeclaredField(SCROLLABLE_TAB_MIN_WIDTH);
            field.setAccessible(true);
            field.set(this, tabMinWidth);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
