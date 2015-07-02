package kr.tomassong.startpack.base;

import android.view.View;

public interface BaseItemClickListener {

    void onItemClick(View view);
    void onItemLongClick(View view);
    void onItemChildClick(View parent, View view);
}
