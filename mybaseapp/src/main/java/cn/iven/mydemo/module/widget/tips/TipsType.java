package cn.iven.mydemo.module.widget.tips;

import android.content.Context;

import cn.iven.mydemo.R;

public class TipsType {

    protected int mLayoutRes;

    public TipsType setHideTarget(boolean hideTarget) {
        mHideTarget = hideTarget;
        return this;
    }

    private boolean mHideTarget;

    public int getOrdinal() {
        return mLayoutRes;
    }

    public TipsType setOrdinal(int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    protected Tips createTips(Context context) {
        return new Tips(context, mLayoutRes, mHideTarget);
    }

}
