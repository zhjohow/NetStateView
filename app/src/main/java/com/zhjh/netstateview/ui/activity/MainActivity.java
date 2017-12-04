package com.zhjh.netstateview.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhjh.netstateview.App;
import com.zhjh.netstateview.R;
import com.zhjh.netstateview.ui.widget.NetStateView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NetStateView.OnRefreshListener {

    private NetStateView netStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        netStateView = (NetStateView) findViewById(R.id.nsv_state_view);

        Button bt_loading = (Button) findViewById(R.id.bt_loading);
        Button bt_error = (Button) findViewById(R.id.bt_error);
        Button bt_noNetwork = (Button) findViewById(R.id.bt_no_network);
        Button bt_empty = (Button) findViewById(R.id.bt_empty);

        bt_loading.setOnClickListener(this);
        bt_error.setOnClickListener(this);
        bt_noNetwork.setOnClickListener(this);
        bt_empty.setOnClickListener(this);

        netStateView.setOnRefreshListener(this);
        netStateView.showLoading();
        showSuccess();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_loading:
                netStateView.showLoading();
                break;

            case R.id.bt_error:
                netStateView.showError();
                break;

            case R.id.bt_no_network:
                netStateView.showNoNetwork();
                break;

            case R.id.bt_empty:
                netStateView.showEmpty();
                break;

            default:
                break;
        }

        showSuccess();
    }

    @Override
    public void onRefresh() {
        netStateView.showLoading();
        showSuccess();
    }

    private void showSuccess() {
        App.getMainThreadHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                netStateView.showSuccess();
            }
        }, 2000);
    }
}
