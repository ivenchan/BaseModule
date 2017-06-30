package cn.iven.mydemo.module.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iven.mydemo.R;
import cn.iven.mydemo.base.BaseHolderRV;
import cn.iven.mydemo.model.bean.GankListBean;

/**
 * Created by iven on 2017/6/19.
 */

public class PicHolder extends BaseHolderRV<GankListBean.ResultsBean> {
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.tv_who)
    TextView mTvWho;

    public PicHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void bindData() {
        Glide.with(mFragment).load(mDataBean.getUrl()).into(mIvPhoto);
        mTvWho.setText(mDataBean.getWho());
    }

    @Override
    protected int currentLayout() {
        return R.layout.item_main_view;
    }

    @OnClick(R.id.iv_photo)
    public void onViewClicked() {
        EventBus.getDefault().post(mDataBean.getWho());
    }
}
