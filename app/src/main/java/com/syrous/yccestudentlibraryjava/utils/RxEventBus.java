package com.syrous.yccestudentlibraryjava.utils;


import android.content.Context;
import android.util.SparseArray;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * Created By : Syrous
 * date : 1/3/20
 */

public final class RxEventBus {

    private static RxEventBus sEventBus;
    private Context context;
    private SparseArray<PublishSubject<Object>> subjectMap = new SparseArray<>();
    private Map<Object, CompositeDisposable> subscriptionMap = new HashMap<>();

    public static final int _UPLOAD_SUBJECT = 1;
    public static final int _ERROR_SUBJECT = 2;
    public static final int _PROGRESS_SUBJECT = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({_UPLOAD_SUBJECT, _ERROR_SUBJECT, _PROGRESS_SUBJECT})
    @interface Subject {

    }


    private RxEventBus(Context context){
        this.context = context;
    }

    public static RxEventBus getBus(Context context){
        if(sEventBus == null){
            sEventBus = new RxEventBus(context);
        }
        return sEventBus;
    }

    public PublishSubject<Object> getSubject(@Subject int subjectCode){
        PublishSubject<Object> subject = subjectMap.get(subjectCode);
        if(subject == null){
            subject = PublishSubject.create();
            subject.subscribeOn(AndroidSchedulers.mainThread());
            subjectMap.put(subjectCode, subject);
        }
        return subject;
    }

    @NonNull
    private CompositeDisposable getCompositeDisposable(@NonNull Object object) {
        CompositeDisposable compositeDisposable = subscriptionMap.get(object);
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            subscriptionMap.put(object, compositeDisposable);
        }
        return compositeDisposable;
    }

    public void subscribe(@Subject int subject, @NonNull Object lifecycle, @NonNull Consumer<Object> action) {
        Disposable disposable = getSubject(subject).subscribe(action);
        getCompositeDisposable(lifecycle).add(disposable);
    }

    public void unregister(@NonNull Object lifecycle) {
        CompositeDisposable compositeDisposable = subscriptionMap.remove(lifecycle);
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public void publish(@Subject int subjectCode, @NonNull Object message) {
        getSubject(subjectCode).onNext(message);
    }
}
