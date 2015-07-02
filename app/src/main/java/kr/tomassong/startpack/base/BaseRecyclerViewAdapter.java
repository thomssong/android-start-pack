package kr.tomassong.startpack.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import kr.tomassong.startpack.data.Item;
import kr.tomassong.startpack.viewholder.ViewHolder;


public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> implements
        View.OnClickListener,
        View.OnLongClickListener{

    private List<Item> items;
    private BaseItemClickListener listener;

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType().getCode();
    }

    public void setItems(List<Item> items) {
        if(this.items == null) {
            this.items = items;
        }
        else{
            int size = items.size();
            this.items.clear();
            notifyItemRangeRemoved(0, size);

            this.items.addAll(items);
        }

        notifyDataSetChanged();
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(int position){
        if(items == null)
            throw new IllegalAccessError("Adapter Data is Null!");
        else
            return items.get(position);
    }

    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void setItemClickListener(BaseItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onItemClick(v);
    }

    @Override
    public boolean onLongClick(View v) {
        if(listener != null){
            listener.onItemLongClick(v);
            return true;
        }
        else {
            return false;
        }
    }
}
