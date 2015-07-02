package kr.tomassong.startpack.data;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Item implements Parcelable{
    private int id;
    private Type type;
    private String name;

    public Item(){}

    protected Item(Parcel in){
        super();
        this.id = in.readInt();
        this.type = (Type) in.readSerializable();
        this.name = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeSerializable(type);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Creator<Item> CREATOR
            = new Creator<Item>(){

        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source){};
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
