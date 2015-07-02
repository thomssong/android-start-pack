package kr.tomassong.startpack.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseListFragment extends Fragment implements BaseItemClickListener {

    protected static final String TAG = "BaseListFragment";

    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter adapter;

    private ListItemCallback mCallback;

    protected abstract int getLayoutResId();

    protected abstract int getRecyclerViewResId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);

        recyclerView = (RecyclerView) view.findViewById(getRecyclerViewResId());

        if (recyclerView != null) {
            setupRecyclerView(recyclerView);
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListItemCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + ListItemCallback.class.getName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    protected abstract void setupRecyclerView(RecyclerView recyclerView);

    protected RecyclerView getRecyclerView() {
        return recyclerView;
    }

    protected BaseRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    protected void setAdapter(BaseRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
        }
    }

    protected ListItemCallback getCallback() {
        return mCallback;
    }

    protected void setCallback(ListItemCallback callback) {
        this.mCallback = callback;
    }
}