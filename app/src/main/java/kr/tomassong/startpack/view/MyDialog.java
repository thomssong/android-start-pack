package kr.tomassong.startpack.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import kr.tomassong.startpack.R;
import kr.tomassong.startpack.data.MyStatus;

public class MyDialog {
    private static final String TAG = "MyDialog";

    private static MyDialog instance;

    final Context context;
    final Handler handler;

    private AlertDialog.Builder builder;

    private MyDialog(Context context, Handler handler, AlertDialog.Builder builder) {
        this.context = context;
        this.handler = handler;
        this.builder = builder;
    }

    public static MyDialog from(Context context){
        return from(context, null);
    }

    public static MyDialog from(Context context, Handler handler){
        if(instance == null){
            synchronized (MyDialog.class){
                if(instance == null){
                    instance = new Builder(context, handler).build();
                }
            }
        }
        return instance;
    }

    /** AlertDialog.Builder 설정 */
    public MyDialog title(String title){
        builder.setTitle(title);
        return instance;
    }
    public MyDialog message(String message){
        builder.setMessage(message);
        return instance;
    }
    public MyDialog positiveBtn(String msg, DialogInterface.OnClickListener listener){
        builder.setPositiveButton(msg, listener);
        return instance;
    }
    public MyDialog negativeBtn(String msg, DialogInterface.OnClickListener listener){
        builder.setNegativeButton(msg, listener);
        return instance;
    }
    public AlertDialog.Builder build(){
        return builder;
    }

    /** Custom Dialog 생성 후 호출 */
    public AlertDialog.Builder build(final MyStatus status){
        return this.build(status, "");
    }

    public AlertDialog.Builder build(final MyStatus status, String msg){
        switch(status){
            case OK:
                break;
            case NETWORK_ERROR:
                builder.setTitle(context.getString(R.string.title_dlg_error))
                        .setMessage(
                                msg.isEmpty() ?
                                        context.getString(status.getMessage()) :
                                        context.getString(status.getMessage()) + " : " + msg
                        )
                        .setNeutralButton(context.getString(R.string.btn_positive), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                break;
        }
        return builder;
    }


    private static class Builder{
        private final Context context;
        private final Handler handler;

        private Builder(Context context, Handler handler) {
            if(context==null){
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context;
            this.handler = handler;
        }

        public MyDialog build(){
            Context context = this.context;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            return new MyDialog(context, handler, builder);
        }
    }
}
