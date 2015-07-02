package kr.tomassong.startpack.data;

import android.os.Parcel;

public class ContentItem extends Item {

    private String image;
    private String desc;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ContentItem(){
        super();
    }

    protected ContentItem(Parcel in){
        super(in);
        image = in.readString();
        desc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(image);
        dest.writeString(desc);
    }
}
