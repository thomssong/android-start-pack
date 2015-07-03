package kr.tomassong.startpack.data;

import java.util.HashMap;
import java.util.Map;

import kr.tomassong.startpack.R;

/**
 *  App Status Data (for AsyncTask, Handler, Dialog Message, etc...)
 */
public enum MyStatus {
    OK(0),
    NETWORK_ERROR(R.string.msg_network_error);

    private int msg;

    private static Map<Integer, MyStatus> map = new HashMap<>();

    static {
        for(MyStatus status : MyStatus.values()){
            map.put(status.msg, status);
        }
    }

    MyStatus(int msg) {
        this.msg = msg;
    }

    public int getMessage() {
        return msg;
    }

    public static MyStatus valueOf(int msg){
        return map.get(msg);
    }
}
