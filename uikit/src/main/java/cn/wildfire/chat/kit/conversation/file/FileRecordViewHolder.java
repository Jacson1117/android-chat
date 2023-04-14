/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.wildfire.chat.kit.conversation.file;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.wildfire.chat.kit.*;
import cn.wildfire.chat.kit.utils.FileUtils;
import cn.wildfirechat.model.Conversation;
import cn.wildfirechat.model.FileRecord;
import cn.wildfirechat.model.UserInfo;
import cn.wildfirechat.remote.ChatManager;

class FileRecordViewHolder extends RecyclerView.ViewHolder {
    TextView fileNameTextView;
    TextView fileSizeTextView;
    ImageView fileIconImageView;
    TextView fileFromTextView;
    TextView fileTimeTextView;

    public FileRecordViewHolder(@NonNull View itemView) {
        super(itemView);
        bindViewImpl(itemView);
    }

    private void bindViewImpl(View itemView) {
        fileNameTextView = itemView.findViewById(R.id.fileNameTextView);
        fileSizeTextView = itemView.findViewById(R.id.fileSizeTextView);
        fileIconImageView = itemView.findViewById(R.id.fileIconImageView);
        fileFromTextView = itemView.findViewById(R.id.fileFromTextView);
        fileTimeTextView = itemView.findViewById(R.id.fileTimeTextView);
    }

    public void onBind(FileRecord fileRecord) {
        fileNameTextView.setText(fileRecord.name);
        fileSizeTextView.setText(FileUtils.getReadableFileSize(fileRecord.size));
        fileIconImageView.setImageResource(FileUtils.getFileTypeImageResId(fileRecord.name));
        UserInfo userInfo;
        if(fileRecord.conversation.type != Conversation.ConversationType.Group) {
            userInfo = ChatManager.Instance().getUserInfo(fileRecord.userId, false);
        } else {
            userInfo = ChatManager.Instance().getUserInfo(fileRecord.userId, fileRecord.conversation.target, false);
        }
        fileFromTextView.setText(" ");
        if(userInfo != null) {
            if (!TextUtils.isEmpty(userInfo.friendAlias)) {
                fileFromTextView.setText("来自 " + userInfo.friendAlias);
            } else if(!TextUtils.isEmpty(userInfo.groupAlias)) {
                fileFromTextView.setText("来自 " + userInfo.groupAlias);
            } else if(!TextUtils.isEmpty(userInfo.displayName)) {
                fileFromTextView.setText("来自 " + userInfo.displayName);
            }
        }
        Date currentTime = new Date(fileRecord.timestamp);
        Calendar c = Calendar.getInstance();
        c.setTime(currentTime);
        int year = c.get(Calendar.YEAR);
        c.setTimeInMillis(System.currentTimeMillis());
        int nowYear = c.get(Calendar.YEAR);

        SimpleDateFormat formatter;
        if(nowYear > year) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            formatter = new SimpleDateFormat("MM-dd");
        }
        fileTimeTextView.setText(formatter.format(currentTime));
    }
}
