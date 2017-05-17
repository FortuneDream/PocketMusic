package com.example.q.pocketmusic.module.home.seek.share;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.q.pocketmusic.R;
import com.example.q.pocketmusic.callback.IDisplayStrategy;
import com.example.q.pocketmusic.model.bean.share.ShareSong;
import com.example.q.pocketmusic.util.GlideStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 鹏君 on 2017/5/16.
 */

public class ShareListAdapter extends RecyclerArrayAdapter<ShareSong> {
    private IDisplayStrategy displayStrategy;

    public ShareListAdapter(Context context) {
        super(context);
        this.displayStrategy = new GlideStrategy();
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UploadViewHolder(parent);
    }

    //上传list的holder
    class UploadViewHolder extends BaseViewHolder<ShareSong> {
        TextView nameTv;
        TextView contentTv;
        ImageView headIv;
        LinearLayout contentRl;

        public UploadViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_combination_upload);
            nameTv = $(R.id.name_tv);
            contentTv = $(R.id.content_tv);
            contentRl = $(R.id.content_rl);
            headIv = $(R.id.head_iv);
        }

        @Override
        public void setData(final ShareSong data) {
            super.setData(data);
            nameTv.setText("上传曲谱：" + data.getName());
            contentTv.setText("描述：" + data.getContent());
            displayStrategy.displayCircle(getContext(), data.getUser().getHeadImg(), headIv);
        }
    }
}