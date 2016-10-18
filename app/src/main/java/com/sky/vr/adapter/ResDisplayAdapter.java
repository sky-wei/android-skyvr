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
import com.sky.vr.data.model.ResourceModel;

/**
 * Created by starrysky on 16-10-10.
 */

public class ResDisplayAdapter extends SimpleRecyclerAdapter<ResourceModel.Resource> {

    public ResDisplayAdapter(Context context) {
        super(context);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, int viewType) {
        return layoutInflater.inflate(R.layout.item_res_display, viewGroup, false);
    }

    @Override
    public BaseRecyclerHolder<ResourceModel.Resource> onCreateViewHolder(View view, int viewType) {
        return new VideoHolder(view, this);
    }

    public class VideoHolder extends BaseRecyclerHolder<ResourceModel.Resource> implements View.OnClickListener {

        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_subtitle;

        public VideoHolder(View itemView, BaseRecyclerAdapter<ResourceModel.Resource> baseRecyclerAdapter) {
            super(itemView, baseRecyclerAdapter);
        }

        @Override
        public void onInitialize() {

            iv_image = (ImageView) findViewById(R.id.iv_image);
            tv_title = (TextView) findViewById(R.id.tv_title);
            tv_subtitle = (TextView) findViewById(R.id.tv_subtitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onBind(int position, int viewType) {

            ResourceModel.Resource resource = getItem(position);

            Glide.with(getContext())
                    .load(resource.getPicUrl())
                    .crossFade()
                    .into(iv_image);

            tv_title.setText(resource.getTitle());
            tv_subtitle.setText(resource.getSubtitle());
        }

        @Override
        public void onClick(View v) {
            // 点击事件
            onItemEvent(0, v, getAdapterPosition());
        }
    }
}
