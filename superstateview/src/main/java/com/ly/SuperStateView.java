package com.ly;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ly.superstateview.R;

/**
 * @version 1.0.0
 * @name 多状态view
 * @date 2019年9月4日 14:36:25
 */
public class SuperStateView extends FrameLayout {
    private Context mContext;
    private View mVLoading, mVEmpty, mVNetError, mVError, mVContent;
    private int SHOW_TYPE = -1; //当前显示类型
    private final int LOADING = 0;//加载
    private final int COTENT = 1; //内容
    private final int EMPTY = 2; //空状态
    private final int NETERROR = 3; //网络错误
    private final int ERROR = 4; //发生错误

    public SuperStateView(@NonNull Context context) {
        this(context, null);
    }

    public SuperStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SuperStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        final TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.SuperStateView);
        int loadId = a.getResourceId(R.styleable.SuperStateView_sup_load, 0);
        int emptyId = a.getResourceId(R.styleable.SuperStateView_sup_empty, 0);
        int contentId = a.getResourceId(R.styleable.SuperStateView_sup_content, 0);
        int errorId = a.getResourceId(R.styleable.SuperStateView_sup_error, 0);
        int netErrorId = a.getResourceId(R.styleable.SuperStateView_sup_unnet, 0);
        addLoadView(loadId);
        addEmptyView(emptyId);
        addContentId(contentId);
        addErrorId(errorId);
        addNetErrorId(netErrorId);
        hidViews();
        a.recycle();
    }


    public void showLoadingView() {
        hidViews();
        if (mVLoading != null) {
            mVLoading.setWillNotDraw(false);
            mVLoading.setVisibility(VISIBLE);
            SHOW_TYPE = LOADING;
        } else {
            Log.e("SuperState:", "not inflate load view");
        }
    }

    public void showEmptyView() {
        hidViews();
        if (mVEmpty != null) {
            mVEmpty.setWillNotDraw(false);
            mVEmpty.setVisibility(VISIBLE);
            SHOW_TYPE = EMPTY;
        } else {
            Log.e("SuperState:", "not inflate empty view");
        }
    }

    public void showContentView() {
        hidViews();
        if (mVContent != null) {
            mVContent.setWillNotDraw(false);
            mVContent.setVisibility(VISIBLE);
            SHOW_TYPE = COTENT;
        } else {
            Log.e("SuperState:", "not inflate content view");
        }
    }

    public void showErrorView() {
        hidViews();
        if (mVError != null) {
            mVNetError.setWillNotDraw(false);
            mVError.setVisibility(VISIBLE);
            SHOW_TYPE = ERROR;
        } else {
            Log.e("SuperState:", "not inflate error view");
        }
    }

    public void showNetErrorView() {
        hidViews();
        if (mVNetError != null) {
            mVNetError.setWillNotDraw(false);
            mVNetError.setVisibility(VISIBLE);
            SHOW_TYPE = NETERROR;
        } else {
            Log.e("SuperState:", "not inflate net error view");
        }
    }

    /**
     * 根据类型展示对应view
     *
     * @param type
     */
    public void showView(int type) {
        hidViews();
        switch (type) {
            case LOADING:
                showLoadingView();
                break;
            case COTENT:
                showContentView();
                break;
            case EMPTY:
                showEmptyView();
                break;
            case ERROR:
                showErrorView();
                break;
            case NETERROR:
                showNetErrorView();
                break;
        }
    }

    public int getShowType() {
        return SHOW_TYPE;
    }

    private void addLoadView(int id) {
        if (id != 0) {
            mVLoading = LayoutInflater.from(mContext).inflate(id, this, false);
            if (mVLoading != null) {
                mVLoading.setWillNotDraw(true);
                addView(mVLoading);
            }
        }
    }

    private void addEmptyView(int id) {
        if (id != 0) {
            mVEmpty = LayoutInflater.from(mContext).inflate(id, this, false);
            if (mVEmpty != null) {
                mVEmpty.setWillNotDraw(true);
                addView(mVEmpty);
            }
        }
    }

    private void addContentId(int id) {
        if (id != 0) {
            mVContent = LayoutInflater.from(mContext).inflate(id, this, false);
            if (mVContent != null) {
                mVContent.setWillNotDraw(true);
                addView(mVContent);
            }
        }
    }

    private void addErrorId(int id) {
        if (id != 0) {
            mVError = LayoutInflater.from(mContext).inflate(id, this, false);
            if (mVError != null) {
                mVError.setWillNotDraw(true);
                addView(mVError);
            }
        }
    }

    private void addNetErrorId(int id) {
        if (id != 0) {
            mVNetError = LayoutInflater.from(mContext).inflate(id, this, false);
            if (mVNetError != null) {
                mVNetError.setWillNotDraw(true);
                addView(mVNetError);
            }
        }
    }


    private void hidViews() {
        if (mVLoading != null) {
            mVLoading.setVisibility(GONE);
        }
        if (mVNetError != null) {
            mVNetError.setVisibility(GONE);
        }
        if (mVError != null) {
            mVError.setVisibility(GONE);
        }
        if (mVEmpty != null) {
            mVEmpty.setVisibility(GONE);
        }
        if (mVContent != null) {
            mVContent.setVisibility(GONE);
        }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    /**
     * 建议在退出activity的时候调用
     */
    public void onDestroy() {
        removeAllViews();
        mVLoading = null;
        mVContent = null;
        mVEmpty = null;
        mVError = null;
        mVNetError = null;
    }
}
