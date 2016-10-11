package com.sky.vr.data.source;

import com.sky.vr.data.mojing.TagsResource;
import com.sky.vr.data.mojing.Tags;

import java.io.IOException;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/**
 * Created by sky on 16-9-29.
 */

public class VideoRepository implements VideoDataSource.Repository {

    private VideoDataSource mLocal;
    private VideoDataSource mRemote;

    public VideoRepository(VideoDataSource local, VideoDataSource remote) {
        mLocal = local;
        mRemote = remote;
    }

    @Override
    public Observable<Tags> getCategory() {

        return Observable.create(new Observable.OnSubscribe<Tags>() {

            @Override
            public void call(Subscriber<? super Tags> subscriber) {

                handler(subscriber, new Callback<Tags>() {
                    @Override
                    public Tags onHandler() throws IOException {
                        try {
                            Tags tags = mLocal.getCategory();
                            if (tags != null) return tags;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return mRemote.getCategory();
                    }
                });
            }
        });
    }

    public <T> void handler(Subscriber<? super T> subscriber, Callback<T> callback) {

        try {
            T t = callback.onHandler();

            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext(t);
            }
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            if (!subscriber.isUnsubscribed()) {
                subscriber.onError(t);
            }
            return;
        }

        if (!subscriber.isUnsubscribed()) {
            subscriber.onCompleted();
        }
    }

    interface Callback<T> {

        T onHandler() throws IOException;
    }

    @Override
    public void saveCategory(Tags tags) {

        mLocal.saveCategory(tags);
        mRemote.saveCategory(tags);
    }

    @Override
    public Observable<TagsResource> getTagsResource(int resId, int tag, int start, int num) {
        return mRemote.getTagsResource(resId, tag, start, num);
    }
}
