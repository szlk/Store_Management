package com.yunsai.ops.store_management.home2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.mvp.MVPBaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class Home2Fragment extends MVPBaseFragment<Home2Contract.View, Home2Presenter> implements Home2Contract.View {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }
}
