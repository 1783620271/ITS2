package com.acdt.cn.its2.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acdt.cn.its2.R;
import com.acdt.cn.its2.ResonJson.Constant;
import com.acdt.cn.its2.ResonJson.HttpUtils;
import com.acdt.cn.its2.ResonJson.Resonjson;
import com.acdt.cn.its2.Utils.BusStation;
import com.acdt.cn.its2.Utils.GetBusStation;

import org.json.JSONException;

import java.util.List;

public class QueryDaoLu extends Activity {

    private static final int FREECAR = 101;
    private TextView busheard;
    private TextView busMiOne;
    private TextView busMiTwo;
    private TextView carHuanJing;
    private Button busAction1;
    private Button busAction2;

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FREECAR:
                    BusStation busStation = busTionList.get(0);
                    busheard.setText("一号站台");
                    busMiOne.setText("一号站台："+busStation.getDistance()+"米");
                    BusStation busStation1 = busTionList.get(1);
                    busMiTwo.setText("二号站台："+busStation1.getDistance()+"米");
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private List<BusStation> busTionList;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.querydaolu);
        intData();
        //点击一号站台的时候
        intiAction1();
    }

    private void intiAction1() {
        busAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                intiData();
            }
        });
    }


    private void intiData() {
        new Thread(){
            @Override
            public void run() {
                try {
                    String doPost = HttpUtils.doPost(Constant.HTTP + Constant.HTTPGETBUSSTATIONINFO, "1");
                    GetBusStation getBusStation = Resonjson.ResolveGetBusStation(doPost);
                    busTionList = getBusStation.getBusTionList();
                    Message msg=new Message();
                    msg.what= FREECAR;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void intData() {
        busheard = (TextView) findViewById(R.id.busheard);
        busMiOne = (TextView) findViewById(R.id.busMiOne);
        busMiTwo = (TextView) findViewById(R.id.busMiTwo);
        carHuanJing = (TextView) findViewById(R.id.carHuanJing);
        busAction1 = (Button) findViewById(R.id.busAction1);
        busAction2 = (Button) findViewById(R.id.busAction2);
    }
}
