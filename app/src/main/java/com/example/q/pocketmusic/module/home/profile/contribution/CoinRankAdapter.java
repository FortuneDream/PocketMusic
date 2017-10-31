package com.example.q.pocketmusic.module.home.profile.contribution;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.q.pocketmusic.R;
import com.example.q.pocketmusic.callback.AbsOnClickItemHeadListener;
import com.example.q.pocketmusic.config.pic.IDisplayStrategy;
import com.example.q.pocketmusic.model.bean.MyUser;
import com.example.q.pocketmusic.config.pic.GlideStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 鹏君 on 2017/3/12.
 */

public class CoinRankAdapter extends RecyclerArrayAdapter<MyUser> {
    private IDisplayStrategy displayStrategy;
    private AbsOnClickItemHeadListener absOnClickItemHeadListener;

    public void setAbsOnClickItemHeadListener(AbsOnClickItemHeadListener absOnClickItemHeadListener) {
        this.absOnClickItemHeadListener = absOnClickItemHeadListener;
    }

    public CoinRankAdapter(Context context) {
        super(context);
        displayStrategy = new GlideStrategy();
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    class ViewHolder extends BaseViewHolder<MyUser> {
        ImageView headIv;
        TextView nickNameTv;
        TextView rankTv;
        TextView coinTv;
        TextView createdAtTv;
        Toolbar contentToolbar;


        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_coin_rank);
            headIv = $(R.id.head_iv);
            rankTv = $(R.id.rank_tv);
            createdAtTv = $(R.id.created_at_tv);
            nickNameTv = $(R.id.nick_name_tv);
            coinTv = $(R.id.coin_tv);
            contentToolbar = $(R.id.content_toolbar);
            contentToolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (absOnClickItemHeadListener != null) {
                        absOnClickItemHeadListener.onClickItem(getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public void setData(final MyUser data) {
            super.setData(data);
            int position = getAdapterPosition() + 1;
            rankTv.setText(position + ".");
            displayStrategy.displayCircle(getContext(), data.getHeadImg(), headIv);
            nickNameTv.setText(data.getNickName());
            createdAtTv.setText("注册时间：" + data.getCreatedAt());
            coinTv.setText(String.valueOf(data.getContribution()) + " 枚");
            headIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (absOnClickItemHeadListener != null) {
                        absOnClickItemHeadListener.onClickHead(getContext(), data);
                    }
                }
            });

        }
    }
}