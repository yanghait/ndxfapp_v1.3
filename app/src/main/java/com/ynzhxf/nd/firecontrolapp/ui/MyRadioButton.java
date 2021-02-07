package com.ynzhxf.nd.firecontrolapp.ui;

import android.content.Context;
//import androidx.appcompat.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatRadioButton;

public class MyRadioButton extends AppCompatRadioButton {
    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
        if (!isChecked()) {
            ((RadioGroup) getParent()).clearCheck();
        }
    }
}
