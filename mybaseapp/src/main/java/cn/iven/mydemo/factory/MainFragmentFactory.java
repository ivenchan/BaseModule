package cn.iven.mydemo.factory;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.iven.mydemo.constant.ConstFragment;
import cn.iven.mydemo.module.ui.ErrorFragment;
import cn.iven.mydemo.module.ui.PicFragment;

/**
 * Created by iven on 2017/6/19.
 */

public class MainFragmentFactory {
    public static Fragment createMainFragment(int position){

        Fragment fragment = PicFragment.newInstance();
        Bundle bundle = new Bundle();

        switch (position) {
            case ConstFragment.Main.MEIZI:
                bundle.putString(ConstFragment.Main.PUT_KEY,ConstFragment.Main.MEIZI_NAME);
                break;
            case ConstFragment.Main.ANDROID:
                fragment = ErrorFragment.newInstance();
                bundle.putString(ConstFragment.Main.PUT_KEY,ConstFragment.Main.MEIZI_NAME);
                break;
            case ConstFragment.Main.IOS:
                fragment = ErrorFragment.newInstance();
                bundle.putString(ConstFragment.Main.PUT_KEY,ConstFragment.Main.MEIZI_NAME);
                break;
            case ConstFragment.Main.VIDEO:
                fragment = ErrorFragment.newInstance();
                bundle.putString(ConstFragment.Main.PUT_KEY,ConstFragment.Main.MEIZI_NAME);
                break;
            case ConstFragment.Main.HTML:
                fragment = ErrorFragment.newInstance();
                bundle.putString(ConstFragment.Main.PUT_KEY,ConstFragment.Main.MEIZI_NAME);
                break;
            case ConstFragment.Main.APP:
                fragment = ErrorFragment.newInstance();
                bundle.putString(ConstFragment.Main.PUT_KEY,ConstFragment.Main.MEIZI_NAME);
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }
}
