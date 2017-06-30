package cn.iven.mydemo.module.widget.tips;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.iven.mydemo.R;


public class DefaultTipsHelper implements TipsHelper {

    public final static int DEFAUL_TTIPS_LOADING = R.layout.tips_loading;
    public final static int DEFAUL_TTIPS_FAILED = R.layout.tips_loading_failed;
    public final static int DEFAUL_TTIP_SEMPTY = R.layout.tips_empty;


    private OnClickTipListener mOnClickTipListener;


    private Context mContext;
    private Toast mToast;
    //要附加的VIEW
    private View mAttachedView;

    private TipsType mTipsType;

    private int mTipsLoadingResId;  //等待加载view资源id
    private int mTipsFailedResId;   //失败错误view资源id
    private int mTipsEmptyResId;    //空数据view资源id


    //true：只显示资源ID false：资源id跟mAttachedView共存
    private boolean mHideTargetLoading = true;
    private boolean mHideTargetFailed = true;
    private boolean mHideTargetEmpty = true;

    public DefaultTipsHelper setOnClickTipListener(OnClickTipListener onClickTipListener) {
        mOnClickTipListener = onClickTipListener;
        return this;
    }

    public DefaultTipsHelper setTipsLoadingResId(int tipsLoadingResId) {
        mTipsLoadingResId = tipsLoadingResId;
        return this;
    }

    public DefaultTipsHelper setTipsFailedResId(int tipsFailedResId) {
        mTipsFailedResId = tipsFailedResId;
        return this;
    }

    public DefaultTipsHelper setTipsEmptyResId(int tipsEmptyResId) {
        mTipsEmptyResId = tipsEmptyResId;
        return this;
    }


    public DefaultTipsHelper setAttachedView(View attachedView) {
        this.mAttachedView = attachedView;
        return this;
    }

    public DefaultTipsHelper setHideTargetLoading(boolean hideTargetLoading) {
        mHideTargetLoading = hideTargetLoading;
        return this;
    }

    public DefaultTipsHelper setHideTargetFailed(boolean hideTargetFailed) {
        mHideTargetFailed = hideTargetFailed;
        return this;
    }

    public DefaultTipsHelper setHideTargetEmpty(boolean hideTargetEmpty) {
        mHideTargetEmpty = hideTargetEmpty;
        return this;
    }

    public DefaultTipsHelper setTipsType(TipsType tipsType) {
        mTipsType = tipsType;
        return this;
    }

    public DefaultTipsHelper(Context context) {
        mContext = context;
        mToast = Toast.makeText(mContext, "", Toast.LENGTH_LONG);
        mTipsType = new TipsType();
    }

    @Override
    public void showEmpty() {
        hideLoading();
        TipsUtils.showTips(mAttachedView,
                mTipsType.setOrdinal(mTipsEmptyResId).setHideTarget(mHideTargetEmpty));
    }

    @Override
    public void hideEmpty() {
        TipsUtils.hideTips(mAttachedView,
                mTipsType.setOrdinal(mTipsEmptyResId));
    }

    @Override
    public void showLoading(boolean firstPage) {
        hideEmpty();
        hideError();
        if (firstPage) {
            if (mTipsLoadingResId == 0) {
                //一个附加的view没有提供
                throw new IllegalArgumentException("View " + mAttachedView + " is not offered");
            }
            View tipsView = TipsUtils.showTips(mAttachedView,
                    mTipsType.setOrdinal(mTipsLoadingResId).setHideTarget(mHideTargetLoading));
//            AnimationDrawable drawable = (AnimationDrawable) ((ImageView) tipsView).getDrawable();
//            drawable.start();
            return;
        }


//        mRefreshLayout.post(new Runnable() {
//          @Override
//          public void run() {
//            mRefreshLayout.setRefreshing(true);
//          }
//        });
    }

    @Override
    public void hideLoading() {
        TipsUtils.hideTips(mAttachedView,
                mTipsType.setOrdinal(mTipsLoadingResId));
    }


    @Override
    public void showError(boolean firstPage, String errorMessage) {
        if (firstPage) {
            View tipsView = TipsUtils.showTips(mAttachedView,
                    mTipsType.setOrdinal(mTipsFailedResId).setHideTarget(mHideTargetFailed));

            View btnTipHelperRetry = tipsView.findViewById(R.id.btn_tip_helper_retry);
            View tvTipHelperMessage = tipsView.findViewById(R.id.tv_tip_helper_message);
            if (!TextUtils.isEmpty(errorMessage) && tvTipHelperMessage != null
                    && tvTipHelperMessage instanceof TextView) {
                ((TextView) tvTipHelperMessage).setText(errorMessage);
            }
            if (btnTipHelperRetry != null && mOnClickTipListener != null) {
                btnTipHelperRetry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnClickTipListener.onTipClick(v, DEFAUL_TTIPS_FAILED);
                    }
                });
            }
            return;
        }
        mToast.setText(errorMessage);
        mToast.show();
    }

    @Override
    public void hideError() {
        TipsUtils.hideTips(mAttachedView,
                mTipsType.setOrdinal(mTipsFailedResId));
    }

    public static class Builder {
        private int mTipsLoadingResId = DEFAUL_TTIPS_LOADING;
        private int mTipsFailedResId = DEFAUL_TTIPS_FAILED;
        private int mTipsEmptyResId = DEFAUL_TTIP_SEMPTY;

        //true：只显示资源ID false：资源id跟mAttachedView共存
        private boolean mHideTargetLoading = true;
        private boolean mHideTargetFailed = true;
        private boolean mHideTargetEmpty = true;

        private View mAttachedView;

        private OnClickTipListener mOnClickTipListener;

        public DefaultTipsHelper.Builder setOnClickTipListener(OnClickTipListener onClickTipListener) {
            mOnClickTipListener = onClickTipListener;
            return this;
        }

        public DefaultTipsHelper.Builder setAttachedView(View attachedView) {
            mAttachedView = attachedView;
            return this;
        }

        public DefaultTipsHelper.Builder setLoading(int resId) {
            mTipsLoadingResId = resId;
            return this;
        }

        public DefaultTipsHelper.Builder setFailed(int resId) {
            mTipsFailedResId = resId;
            return this;
        }

        public DefaultTipsHelper.Builder setEmpty(int resId) {
            mTipsEmptyResId = resId;
            return this;
        }

        public DefaultTipsHelper.Builder setHideTargetLoading(boolean hideTargetLoading) {
            mHideTargetLoading = hideTargetLoading;
            return this;
        }

        public DefaultTipsHelper.Builder setHideTargetFailed(boolean hideTargetFailed) {
            mHideTargetFailed = hideTargetFailed;
            return this;
        }

        public DefaultTipsHelper.Builder setHideTargetEmpty(boolean hideTargetEmpty) {
            mHideTargetEmpty = hideTargetEmpty;
            return this;
        }

        public DefaultTipsHelper create(Context context) {
            DefaultTipsHelper defaultTipsHelper = new DefaultTipsHelper(context);
            defaultTipsHelper.setTipsEmptyResId(mTipsEmptyResId)
                    .setTipsFailedResId(mTipsFailedResId)
                    .setTipsLoadingResId(mTipsLoadingResId)
                    .setOnClickTipListener(mOnClickTipListener)
                    .setHideTargetEmpty(mHideTargetEmpty)
                    .setHideTargetLoading(mHideTargetLoading)
                    .setHideTargetFailed(mHideTargetFailed);


            if (mAttachedView == null) {
                //一个附加的view没有提供
                throw new IllegalArgumentException("View " + mAttachedView + " is not offered");
            }
            defaultTipsHelper.setAttachedView(mAttachedView);

            return defaultTipsHelper;
        }
    }


    public interface OnClickTipListener {
        void onTipClick(View v, int status);
    }
}
