package com.example.q.pocketmusic.module.home.seek.share;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.q.pocketmusic.R;
import com.example.q.pocketmusic.callback.AbsOnClickItemHeadListener;
import com.example.q.pocketmusic.config.pic.IDisplayStrategy;
import com.example.q.pocketmusic.model.bean.share.ShareSong;
import com.example.q.pocketmusic.config.pic.GlideStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 鹏君 on 2017/5/16.
 */

public class ShareListAdapter extends RecyclerArrayAdapter<ShareSong> {
    private IDisplayStrategy displayStrategy;
    private AbsOnClickItemHeadListener absOnClickItemHeadListener;

    public void setAbsOnClickItemHeadListener(AbsOnClickItemHeadListener absOnClickItemHeadListener) {
        this.absOnClickItemHeadListener = absOnClickItemHeadListener;
    }

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
        TextView agreeNumTv;
        TextView downloadNumTv;
        ImageView headIv;
        LinearLayout contentRl;

        public UploadViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_combination_upload);
            nameTv = $(R.id.name_tv);
            contentTv = $(R.id.content_tv);
            contentRl = $(R.id.content_rl);
            headIv = $(R.id.head_iv);
            agreeNumTv = $(R.id.agree_num_tv);
            downloadNumTv = $(R.id.download_num_tv);
            contentRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (absOnClickItemHeadListener != null) {
                        absOnClickItemHeadListener.onClickItem(getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public void setData(final ShareSong data) {
            super.setData(data);
            nameTv.setText("上传曲谱：" + data.getName());
            contentTv.setText("描述：" + data.getContent());
            displayStrategy.displayCircle(getContext(), data.getUser().getHeadImg(), headIv);
            headIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (absOnClickItemHeadListener != null) {
                        absOnClickItemHeadListener.onClickHead(getContext(), data.getUser());
                    }
                }
            });
            downloadNumTv.setText(String.valueOf(data.getDownloadNum()));
            agreeNumTv.setText(String.valueOf(data.getAgreeNum()));
        }
    }
}
