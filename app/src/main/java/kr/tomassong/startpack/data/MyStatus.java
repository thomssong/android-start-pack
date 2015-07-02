package kr.tomassong.startpack.data;

import java.util.HashMap;
import java.util.Map;

/**
 *  App Status Data (for AsyncTask, Handler, Dialog Message, etc...)
 */
public enum MyStatus {
    OK(0),
    INSTALL(1); //R.string.msg_install_epub

    private int code;

    private static Map<Integer, MyStatus> map = new HashMap<>();

    static {
        for(MyStatus status : MyStatus.values()){
            map.put(status.code, status);
        }
    }

    MyStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MyStatus valueOf(int code){
        return map.get(code);
    }
}
