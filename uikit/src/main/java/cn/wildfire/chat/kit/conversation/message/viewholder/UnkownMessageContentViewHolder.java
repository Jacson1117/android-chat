/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.wildfire.chat.kit.conversation.message.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import cn.wildfire.chat.kit.*;
import cn.wildfire.chat.kit.annotation.EnableContextMenu;
import cn.wildfire.chat.kit.annotation.MessageContentType;
import cn.wildfire.chat.kit.conversation.ConversationFragment;
import cn.wildfire.chat.kit.conversation.message.model.UiMessage;
import cn.wildfirechat.message.UnknownMessageContent;

@MessageContentType(UnknownMessageContent.class)
@EnableContextMenu
public class UnkownMessageContentViewHolder extends NormalMessageContentViewHolder {
    TextView contentTextView;

    public UnkownMessageContentViewHolder(ConversationFragment fragment, RecyclerView.Adapter adapter, View itemView) {
        super(fragment, adapter, itemView);
        bindViewImpl(itemView);
    }

    private void bindViewImpl(View itemView) {
        contentTextView =itemView.findViewById(R.id.contentTextView);
    }

    @Override
    public void onBind(UiMessage message) {
        contentTextView.setText("暂不支持此消息，请升级最新版本!");
    }

}
