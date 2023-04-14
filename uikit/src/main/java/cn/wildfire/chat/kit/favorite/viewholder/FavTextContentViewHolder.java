/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.wildfire.chat.kit.favorite.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cn.wildfire.chat.kit.*;
import cn.wildfire.chat.kit.WfcWebViewActivity;
import cn.wildfire.chat.kit.favorite.FavoriteItem;

public class FavTextContentViewHolder extends FavContentViewHolder {
    TextView favTextContentTextView;

    public FavTextContentViewHolder(@NonNull View itemView) {
        super(itemView);
        bindViewImpl(itemView);
        bindClickImpl(itemView);
    }

    private void bindClickImpl(View itemView) {
        itemView.findViewById(R.id.favTextContentTextView).setOnClickListener(_v -> showFavText());
    }

    private void bindViewImpl(View itemView) {
        favTextContentTextView = itemView.findViewById(R.id.favTextContentTextView);
    }

    @Override
    public void bind(Fragment fragment, FavoriteItem item) {
        super.bind(fragment, item);
        favTextContentTextView.setText(item.getTitle());
    }

    void showFavText() {
        WfcWebViewActivity.loadHtmlContent(fragment.getActivity(), "收藏内容", favoriteItem.getTitle());
    }
}
