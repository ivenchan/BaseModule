package cn.iven.mydemo.module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import javax.inject.Inject;

import cn.iven.mydemo.constant.ConstFragment;
import cn.iven.mydemo.factory.MainFragmentFactory;

/**
 * Created by iven on 2017/6/19.
 */

public class PicPageAdapter extends FragmentPagerAdapter {
    @Inject
    public PicPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragmentFactory.createMainFragment(position);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case ConstFragment.Main.MEIZI:
                title = ConstFragment.Main.MEIZI_NAME;
                break;
            case ConstFragment.Main.ANDROID:
                title = ConstFragment.Main.ANDROID_NAME;
                break;
            case ConstFragment.Main.IOS:
                title = ConstFragment.Main.IOS_NAME;
                break;
            case ConstFragment.Main.VIDEO:
                title = ConstFragment.Main.VIDEO_NAME;
                break;
            case ConstFragment.Main.HTML:
                title = ConstFragment.Main.HTML_NAME;
                break;
            case ConstFragment.Main.APP:
                title = ConstFragment.Main.APP_NAME;
                break;
        }
        return title;
    }
}
