package kr.tomassong.startpack.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import kr.tomassong.startpack.data.Item;

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindItem(Item item);
}
