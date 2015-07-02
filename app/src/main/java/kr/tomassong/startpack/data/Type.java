package kr.tomassong.startpack.data;

import java.util.HashMap;
import java.util.Map;

import kr.tomassong.startpack.R;


public enum Type {
    TRANSPORT(R.layout.item_transport),
    FOOD(R.layout.item_food),
    ANIMAL(R.layout.item_animal),
    CITY(R.layout.item_city);

    private int code;

    private static Map<Integer, Type> map = new HashMap<>();

    static {
        for(Type type : Type.values()){
            map.put(type.code, type);
        }
    }

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Type valueOf(int code){
        return map.get(code);
    }
}
