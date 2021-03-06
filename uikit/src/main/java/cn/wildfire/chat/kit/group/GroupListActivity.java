/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.wildfire.chat.kit.group;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import cn.wildfire.chat.kit.WfcBaseActivity;
import cn.wildfire.chat.kit.R;
import cn.wildfire.chat.kit.R2;
import cn.wildfire.chat.kit.channel.ChannelListFragment;
import cn.wildfirechat.model.GroupInfo;

public class GroupListActivity extends WfcBaseActivity {

    private boolean forResult;
    private boolean pick;
    /**
     * intent里面置为{@code true}时，返回groupInfo，不直接打开群会话界面
     */
    public static final String INTENT_FOR_RESULT = "forResult";

    /**
     * for result时，单选，还是多选？
     */
    // TODO
    public static final String MODE_SINGLE = "single";
    public static final String MODE_MULTI = "multi";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pick = getIntent().getBooleanExtra("pick", false);
        super.onCreate(savedInstanceState);
    }

    // TODO activity or fragment?
    public static Intent buildIntent(boolean pickForResult, boolean isMultiMode) {

        return null;
    }

    @Override
    protected int contentLayout() {
        return R.layout.fragment_container_activity;
    }

    @Override
    protected void afterViews() {
        if(pick){
            Bundle bundle = new Bundle();
            bundle.putBoolean("pick", pick);
            GroupListFragment fragment = new GroupListFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFrameLayout, fragment)
                    .commit();
        }
        else {
            forResult = getIntent().getBooleanExtra(INTENT_FOR_RESULT, false);
            GroupListFragment fragment = new GroupListFragment();
            if (forResult) {
                fragment.setOnGroupItemClickListener(groupInfo -> {
                    Intent intent = new Intent();
                    // TODO 多选
                    ArrayList<GroupInfo> groupInfos = new ArrayList<>();
                    groupInfos.add(groupInfo);
                    intent.putParcelableArrayListExtra("groupInfos", groupInfos);
                    setResult(RESULT_OK, intent);
                    finish();
                });
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFrameLayout, fragment)
                    .commit();
        }
    }
}
