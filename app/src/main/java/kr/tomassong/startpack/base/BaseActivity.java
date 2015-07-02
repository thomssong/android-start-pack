package kr.tomassong.startpack.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {

    protected static final String TAG = "BaseActivity";

    protected abstract int getLayoutResId();
    protected abstract Toolbar getToolbar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResId());

        Toolbar toolbar = getToolbar();
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }

}
