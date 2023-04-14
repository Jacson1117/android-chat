/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.wildfire.chat.kit.group;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import cn.wildfire.chat.kit.GlideApp;
import cn.wildfire.chat.kit.R;
import cn.wildfirechat.model.GroupInfo;

public class GroupViewHolder extends RecyclerView.ViewHolder {
    protected Fragment fragment;
    private GroupListAdapter adapter;
    ImageView portraitImageView;
    TextView nameTextView;
    TextView categoryTextView;
    View dividerLine;

    protected GroupInfo groupInfo;

    public GroupViewHolder(Fragment fragment, GroupListAdapter adapter, View itemView) {
        super(itemView);
        this.fragment = fragment;
        this.adapter = adapter;
        bindViewImpl(itemView);
    }

    private void bindViewImpl(View itemView) {
        portraitImageView = itemView.findViewById(R.id.portraitImageView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        categoryTextView = itemView.findViewById(R.id.categoryTextView);
        dividerLine = itemView.findViewById(R.id.dividerLine);
    }

    // TODO hide the last diver line
    public void onBind(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
        categoryTextView.setVisibility(View.GONE);
        nameTextView.setText(!TextUtils.isEmpty(groupInfo.remark) ? groupInfo.remark : groupInfo.name);
        GlideApp.with(fragment).load(this.groupInfo.portrait).placeholder(R.mipmap.ic_group_chat).into(portraitImageView);
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }
}
