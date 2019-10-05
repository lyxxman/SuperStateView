package com.ly;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ly.superstateview.R;

/**
 * @version 2.0.0 使用viewstub 添加懒加载
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
    private ViewStub mStbLoading, mStbEmpty, mStbNetError, mStbError, mStbContent;
    public int layLoadingId, layEmptyId, layNetErrorId, layErrorId, layContentId;

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
        if (mStbLoading != null) {
            if (mVLoading == null) {
                mVLoading = mStbLoading.inflate();
            } else
                mVLoading.setVisibility(VISIBLE);
            SHOW_TYPE = LOADING;
        } else {
            Log.e("SuperState:", "not inflate load view");
        }
    }

    public void showEmptyView() {
        hidViews();
        if (mStbEmpty != null) {
            if (mVEmpty == null) {
                mVEmpty = mStbEmpty.inflate();
            } else
                mVEmpty.setVisibility(VISIBLE);
            SHOW_TYPE = EMPTY;
        } else {
            Log.e("SuperState:", "not inflate empty view");
        }
    }

    public void showContentView() {
        hidViews();
        if (mStbContent != null) {
            if (mVContent == null) {
                mVContent = mStbContent.inflate();
            } else
                mVContent.setVisibility(VISIBLE);
            SHOW_TYPE = COTENT;
        } else {
            Log.e("SuperState:", "not inflate content view");
        }
    }

    public void showErrorView() {
        hidViews();
        if (mStbError != null) {
            if (mVError == null) {
                mVError = mStbError.inflate();
            } else {
                mVError.setVisibility(VISIBLE);
            }
            SHOW_TYPE = ERROR;
        } else {
            Log.e("SuperState:", "not inflate error view");
        }
    }

    public void showNetErrorView() {
        hidViews();
        if (mStbNetError != null) {
            if (mVNetError == null) {
                mVNetError = mStbNetError.inflate();
            } else {
                mVNetError.setVisibility(VISIBLE);
            }
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
            mStbLoading = new ViewStub(mContext);
            layLoadingId = View.generateViewId();
            mStbLoading.setInflatedId(layLoadingId);
            mStbLoading.setLayoutResource(id);
            if (mStbLoading != null) {
                addView(mStbLoading);
            }
        }
    }

    private void addEmptyView(int id) {
        if (id != 0) {
            mStbEmpty = new ViewStub(mContext);
            layEmptyId = View.generateViewId();
            mStbEmpty.setInflatedId(layEmptyId);
            mStbEmpty.setLayoutResource(id);
            if (mStbEmpty != null) {
                addView(mStbEmpty);
            }
        }
    }

    private void addContentId(int id) {
        if (id != 0) {
            mStbContent = new ViewStub(mContext);
            layContentId = View.generateViewId();
            mStbContent.setInflatedId(layContentId);
            mStbContent.setLayoutResource(id);
            if (mStbContent != null) {
                addView(mStbContent);
            }
        }
    }

    private void addErrorId(int id) {
        if (id != 0) {
            mStbError = new ViewStub(mContext);
            layErrorId = View.generateViewId();
            mStbError.setInflatedId(layErrorId);
            mStbError.setLayoutResource(id);
            if (mStbError != null) {
                addView(mStbError);
            }
        }
    }

    private void addNetErrorId(int id) {
        if (id != 0) {
            mStbNetError = new ViewStub(mContext);
            layNetErrorId = View.generateViewId();
            mStbNetError.setInflatedId(layNetErrorId);
            mStbNetError.setLayoutResource(id);
            if (mStbNetError != null) {
                addView(mStbNetError);
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
        mStbNetError=null;
        mStbError =null;
        mStbEmpty=null;
        mStbLoading =null;
        mStbContent=null;
        mVLoading = null;
        mVContent = null;
        mVEmpty = null;
        mVError = null;
        mVNetError = null;
    }
}
