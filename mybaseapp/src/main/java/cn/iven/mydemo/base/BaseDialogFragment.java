package cn.iven.mydemo.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by iven on 2017/6/19.
 */

public abstract class BaseDialogFragment extends DialogFragment {
    private View mViewContent;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewCompat.setBackground(getDialog().getWindow().getDecorView(), new ColorDrawable());
        if (mViewContent == null) {
            mViewContent = inflater.inflate(getLayoutResid(), container, false);
            ButterKnife.bind(this, mViewContent);
            initUI();
        } else {
            reMoveParent(mViewContent);
        }
        return mViewContent;
    }

    protected abstract int getLayoutResid();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initUI() {

    }

    protected void initData() {

    }

    /**
     * 移除父类
     *
     * @param view
     */
    private void reMoveParent(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
    }


}
