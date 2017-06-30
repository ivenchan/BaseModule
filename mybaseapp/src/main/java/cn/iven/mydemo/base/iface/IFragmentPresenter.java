package cn.iven.mydemo.base.iface;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by iven on 2017/6/19.
 */

public interface IFragmentPresenter {
    void setUserVisibleHint(boolean isVisibleToUser);

    void onAttach();

    void onCreate(@Nullable Bundle savedInstanceState);

    void onCreateView(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onStop();

    void onDestroy();

    void onPause();

    void onDetach();

    void onViewCreated( @Nullable Bundle savedInstanceState);

    void onActivityCreated(@Nullable Bundle savedInstanceState);

    void onDestroyView();

    void onActivityResult(int requestCode,int resultCode,Bundle data);
}
