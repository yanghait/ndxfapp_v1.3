package com.ynzhxf.nd.firecontrolapp.ui;

import com.github.mikephil.charting.formatter.ValueFormatter;
import com.ynzhxf.nd.firecontrolapp.util.LogUtils;

import java.util.List;

/**
 * Created by nd on 2018-07-16.
 */

public class MyXFormatter extends ValueFormatter {
    private List<String> mValues;

    public MyXFormatter(List<String> values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value) {
        if (((int) value >= 0 && (int) value < mValues.size())) {
            return mValues.get((int) value);
        } else
            return "";
    }
}
