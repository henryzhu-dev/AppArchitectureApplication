package com.zhl.lib_core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.zhl.lib_core.utils.AppTaskManager;
import com.zhl.lib_core.utils.NetworkUtil;

/**
 * author : zhuhl
 * date   : 2021/9/1
 * desc   : 网络状况变化监控广播
 * refer  : http://valuesfeng.github.io/2015/12/09/Android-%E6%A3%80%E6%B5%8B%E5%88%87%E6%8D%A2%E7%BD%91%E7%BB%9C%E7%8A%B6%E6%80%81/
 *
 * 使用（注册）：
 * if (networkStateReceiver == null) {
 *             networkStateReceiver = new NetworkStateReceiver();
 *         }
 *         networkStateReceiver.setListener(this);
 *         IntentFilter filter = new IntentFilter();
 *         filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
 *         registerReceiver(networkStateReceiver, filter);
 *
 * 取消注册：
 * unregisterReceiver(networkStateReceiver);
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    private NetworkChangeListener listener;

    private NetworkUtil.NetworkState currentStatus = NetworkUtil.NetworkState.TYPE_NOT_CONNECTED;

    private Handler handler;

    public void setListener(NetworkChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (handler == null) {
            handler = new Handler();
        }
        handler.removeMessages(Message.obtain().what);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NetworkUtil.NetworkState status = NetworkUtil.INSTANCE.getNetWorkState(AppTaskManager.INSTANCE.getContext());
                if (status != currentStatus) {
                    currentStatus = status;
                    //EventBus.getDefault().post(new EventNetworkChange(status));
                    if (status == NetworkUtil.NetworkState.TYPE_WIFI) { //切换到wifi
                        if (listener != null) {
                            listener.wifiEnabled();
                        }
                    }else if (status == NetworkUtil.NetworkState.TYPE_MOBILE) {//切换为手机网络了
                        if (listener != null) {
                            listener.mobileEnabled();
                        }
                    } else if (status == NetworkUtil.NetworkState.TYPE_NOT_CONNECTED) {//所有网络都关了
                        if (listener != null) {
                            listener.networkDisabled();
                        }
                    }
                }
                handler.removeMessages(Message.obtain().what);
                handler = null;
            }
        }, 2000);
    }

    public interface NetworkChangeListener {

        void wifiEnabled();

        void mobileEnabled();

        void networkDisabled();
    }
}
