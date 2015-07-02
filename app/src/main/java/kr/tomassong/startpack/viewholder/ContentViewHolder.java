package kr.tomassong.startpack.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import kr.tomassong.startpack.R;
import kr.tomassong.startpack.data.ContentItem;
import kr.tomassong.startpack.data.Item;
import kr.tomassong.startpack.data.Type;
import kr.tomassong.startpack.view.CircleTransform;

/**
 * Created by Tomas on 15. 7. 2.
 * Copyright (C) 2013. 5.   Orange Digit Inc. All rights reserved.
 * ViewPorter® is Trademark of Orange Digit Inc.
 * Prohibited to copy or distribute.
 */
public class ContentViewHolder extends ViewHolder {

    private TextView title;
    private ImageView cover;
    private TextView description;

    public ContentViewHolder(Type type, View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        cover = (ImageView) itemView.findViewById(R.id.cover);

        if(type == Type.FOOD){
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }

    @Override
    public void bindItem(Item item) {
        title.setText(item.getName());

        if(item.getType() == Type.FOOD){
            description.setText(((ContentItem)item).getDesc());
        }

        if(item.getType() == Type.ANIMAL){
            Glide.with(itemView.getContext())
                    .load(((ContentItem)item).getImage())
                    .placeholder(itemView.getResources().getColor(android.R.color.darker_gray))
                    .error(itemView.getResources().getColor(android.R.color.holo_red_light))
                    .transform(new CircleTransform(itemView.getContext()))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(cover);
        }
        else {
            Glide.with(itemView.getContext())
                    .load(((ContentItem)item).getImage())
                    .placeholder(itemView.getResources().getColor(android.R.color.darker_gray))
                    .error(itemView.getResources().getColor(android.R.color.holo_red_light))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(cover);
        }
    }
}
