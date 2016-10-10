package com.sky.vr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.android.common.adapter.SimpleRecyclerAdapter;
import com.sky.android.common.base.BaseRecyclerAdapter;
import com.sky.android.common.base.BaseRecyclerHolder;
import com.sky.vr.R;
import com.sky.vr.model.ResourceModel;

/**
 * Created by starrysky on 16-10-10.
 */

public class VideoAdapter extends SimpleRecyclerAdapter<ResourceModel.Resource> {

    public VideoAdapter(Context context) {
        super(context);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, int viewType) {
        return layoutInflater.inflate(R.layout.item_video, viewGroup, false);
    }

    @Override
    public BaseRecyclerHolder<ResourceModel.Resource> onCreateViewHolder(View view, int viewType) {
        return new VideoHolder(view, this);
    }

    public class VideoHolder extends BaseRecyclerHolder<ResourceModel.Resource> implements View.OnClickListener {

        private ImageLoader mImageLoader;
        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_subtitle;

        public VideoHolder(View itemView, BaseRecyclerAdapter<ResourceModel.Resource> baseRecyclerAdapter) {
            super(itemView, baseRecyclerAdapter);
        }

        @Override
        public void onInitialize() {

            mImageLoader = ImageLoader.getInstance();

            iv_image = (ImageView) findViewById(R.id.iv_image);
            tv_title = (TextView) findViewById(R.id.tv_title);
            tv_subtitle = (TextView) findViewById(R.id.tv_subtitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onBind(int position, int viewType) {

            ResourceModel.Resource resource = getItem(position);

            mImageLoader.displayImage(resource.getPicUrl(), iv_image);
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
