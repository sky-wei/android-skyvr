package com.sky.vr.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.android.common.adapter.SimpleRecyclerAdapter;
import com.sky.android.common.base.BaseRecyclerAdapter;
import com.sky.android.common.base.BaseRecyclerHolder;
import com.sky.vr.R;
import com.sky.vr.base.PresenterFragment;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.presenter.VideoPresenter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 16-9-27.
 */
@EFragment(R.layout.fragment_page)
public class VideoFragment extends PresenterFragment<VideoContract.Presenter> implements VideoContract.View {

    @ViewById(R.id.recycler_view)
    public RecyclerView recycler_view;

    private int resId;
    private int tag;

    @Override
    public void initView() {

        // 获取参数信息
        resId = getArguments().getInt("resId");
        tag = getArguments().getInt("tag");

        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

        // 初始化
        mPresenter = new VideoPresenter(getContext(), this);
        mPresenter.loadTagsResource(resId, tag);
    }

    private List<String> buildItem() {

        List<String> items = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            items.add("I: " + i);
        }

        return items;
    }

    public class PageAdapter extends SimpleRecyclerAdapter<String> {

        public PageAdapter(Context context) {
            super(context);
        }

        @Override
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            return layoutInflater.inflate(R.layout.item_page, viewGroup, false);
        }

        @Override
        public BaseRecyclerHolder<String> onCreateViewHolder(View view, int i) {
            return new PageViewHolder(view, this);
        }
    }

    public class PageViewHolder extends BaseRecyclerHolder<String> {

        public PageViewHolder(View itemView, BaseRecyclerAdapter<String> baseRecyclerAdapter) {
            super(itemView, baseRecyclerAdapter);
        }

        @Override
        public void onInitialize() {

        }

        @Override
        public void onBind(int i, int i1) {

        }
    }
}
