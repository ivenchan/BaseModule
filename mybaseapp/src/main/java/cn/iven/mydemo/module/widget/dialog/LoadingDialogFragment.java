package cn.iven.mydemo.module.widget.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import cn.iven.mydemo.R;
import cn.iven.mydemo.base.BaseDialogFragment;
import cn.iven.mydemo.base.iface.ILoading;

/**
 * Created by iven on 2017/6/19.
 */

public class LoadingDialogFragment extends BaseDialogFragment implements ILoading {
    @BindView(R.id.tv_tip_desc)
    TextView mTvTipDesc;

    public void setFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    FragmentManager mFragmentManager;

    public void setTipDesc(String tipDesc) {
        if (mTvTipDesc != null) {
            mTvTipDesc.setText(tipDesc);
        }
        mTipDesc = tipDesc;
    }

    private String mTipDesc;

    @Override
    protected int getLayoutResid() {
        return R.layout.dialog_loading_view;
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(mTipDesc)) {
            mTvTipDesc.setText(mTipDesc);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.TOP;
    }


    @Override
    public void showLoading() {
        if (!isAdded()) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.remove(this);
            fragmentTransaction.commit();
            show(mFragmentManager, this.getClass().getSimpleName());
        }
    }

    @Override
    public void hideLoading() {
        if (isAdded()) {
            this.dismiss();
        }
    }
}
