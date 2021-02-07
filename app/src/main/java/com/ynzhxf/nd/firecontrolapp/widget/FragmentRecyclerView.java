package com.ynzhxf.nd.firecontrolapp.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

import com.ynzhxf.nd.firecontrolapp.util.LogUtils;


/**
 * author hbzhou
 * date 2019/6/21 11:48
 */
public class FragmentRecyclerView extends RecyclerView {

    private int height = 0;

    public FragmentRecyclerView(@NonNull Context context) {
        super(context);
    }

    public FragmentRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);

        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        height = getMeasuredHeight();
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), height);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(computeVerticalScrollRange(), MeasureSpec.EXACTLY));
    }

    public int getRealHeight() {
        return height;
    }
}
