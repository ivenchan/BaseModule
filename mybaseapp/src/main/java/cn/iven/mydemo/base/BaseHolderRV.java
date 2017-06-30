package cn.iven.mydemo.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by iven on 2017/6/19.
 */

public abstract class BaseHolderRV<T> extends RecyclerView.ViewHolder {

    protected int mPosition;
    protected int mPositionOffSet;
    protected T mDataBean;
    protected Context mContext;
    protected View mItemView;


    protected Fragment mFragment;

    public BaseHolderRV(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mItemView = itemView;
        mContext = itemView.getContext();
        initData();
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }


    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public void setData(T t, int position, int offset) {
        this.mDataBean = t;
        mPosition = position;
        mPositionOffSet = offset;
        bindData();
    }

    /**
     * 填充数据
     */
    protected abstract void bindData();


    protected int currentLayout() {
        return 0;
    }
}
