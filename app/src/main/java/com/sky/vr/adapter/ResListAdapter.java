package com.sky.vr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sky.android.common.adapter.SimpleRecyclerAdapter;
import com.sky.android.common.base.BaseRecyclerAdapter;
import com.sky.android.common.base.BaseRecyclerHolder;
import com.sky.vr.R;
import com.sky.vr.data.model.ResListModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by starrysky on 16-10-10.
 */

public class ResListAdapter extends SimpleRecyclerAdapter<ResListModel.Resource> {

    public ResListAdapter(Context context) {
        super(context);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, int viewType) {
        return layoutInflater.inflate(R.layout.item_res_list, viewGroup, false);
    }

    @Override
    public BaseRecyclerHolder<ResListModel.Resource> onCreateViewHolder(View view, int viewType) {
        return new VideoHolder(view, this);
    }

    public class VideoHolder extends BaseRecyclerHolder<ResListModel.Resource> {

        @BindView(R.id.iv_image)
        ImageView iv_image;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_subtitle)
        TextView tv_subtitle;

        public VideoHolder(View itemView, BaseRecyclerAdapter<ResListModel.Resource> baseRecyclerAdapter) {
            super(itemView, baseRecyclerAdapter);
        }

        @Override
        public void onInitialize() {
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position, int viewType) {

            ResListModel.Resource resource = getItem(position);

            Glide.with(getContext())
                    .load(resource.getPicUrl())
                    .crossFade()
                    .into(iv_image);

            tv_title.setText(resource.getTitle());
            tv_subtitle.setText(resource.getSubtitle());
        }



        @OnClick(R.id.card_res_item)
        public void onClick(View v) {
            // 点击事件
            onItemEvent(0, v, getAdapterPosition());
        }
    }
}
