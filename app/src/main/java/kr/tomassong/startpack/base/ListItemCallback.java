package kr.tomassong.startpack.base;

import android.view.View;

import kr.tomassong.startpack.data.Item;


public interface ListItemCallback {
    void onItemSelected(Item item, View transitionView);
    void onMenuSelected(Item item);
}
