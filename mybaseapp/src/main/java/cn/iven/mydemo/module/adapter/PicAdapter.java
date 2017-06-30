package cn.iven.mydemo.module.adapter;

import android.support.v4.app.Fragment;
import android.view.View;

import javax.inject.Inject;

import cn.iven.mydemo.R;
import cn.iven.mydemo.base.BaseAdapterRV;
import cn.iven.mydemo.base.BaseHolderRV;
import cn.iven.mydemo.module.holder.PicHolder;

/**
 * Created by iven on 2017/6/19.
 *
 */

public class PicAdapter extends BaseAdapterRV {

    @Inject
    public PicAdapter(Fragment fragment) {
        super(fragment);
    }

    @Override
    protected int getLayoutResID(int viewType) {
        return R.layout.item_main_view;
    }

    @Override
    protected BaseHolderRV createViewHolder(View view, int viewType) {
        return new PicHolder(view);
    }
}
