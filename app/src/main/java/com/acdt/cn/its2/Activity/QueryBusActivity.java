package com.acdt.cn.its2.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.acdt.cn.its2.R;
import com.acdt.cn.its2.ResonJson.Constant;
import com.acdt.cn.its2.ResonJson.HttpUtils;
import com.acdt.cn.its2.ResonJson.Resonjson;
import com.acdt.cn.its2.Utils.GetParkFree;
import com.acdt.cn.its2.Utils.KongXianBus;

import org.json.JSONException;

import java.util.List;

import static android.content.ContentValues.TAG;

public class QueryBusActivity extends Activity {

    private static final int FREECAR = 101;
    private Button feiLvQuery;
    private EditText feiLv;
    private Button setting;
    private Button freeCarquery;
    private ImageView freeCar;
    private ImageView freeCar1;

    private KongXianBus parkFreeId;
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  FREECAR:
                    for(int i=0;i<parkFreeIdLit.size();i++){
                        KongXianBus kongXianBus=new KongXianBus();
                        kongXianBus = parkFreeIdLit.get(i);
                        if(kongXianBus.getBuwei()==1){
                            freeCar.setBackgroundResource(R.drawable.parknotfree);
                        }else{
                            freeCar.setBackgroundResource(R.drawable.parkfree);
                        }
                        if(kongXianBus.getBuwei()==2){
                            freeCar1.setBackgroundResource(R.drawable.parknotfree);
                        }else{
                            freeCar1.setBackgroundResource(R.drawable.parkfree);
                        }
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private List<KongXianBus> parkFreeIdLit;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.querybus);
        //声明布局控件
        intiData();
        intiFreeCar();
    }

    private void intiFreeCar() {
        freeCarquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                intiFreeshuju();
            }
        });
    }

    private void intiFreeshuju() {
        new Thread(){
            @Override
            public void run() {
               // Log.i(TAG, "run: 222222222222");
                String doPost = HttpUtils.doPost(Constant.HTTP + Constant.HTTPGETPARKFREE, null);
                try {
                    GetParkFree getParkFree = Resonjson.ResolveGetParkFree(doPost);
                    parkFreeIdLit = getParkFree.getParkFreeIdLit();
                    //Log.i(TAG, "run: 11"+parkFreeIdLit.toString());
                    Message msg=new Message();
                    msg.what=FREECAR;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void intiData() {
        feiLvQuery = (Button) findViewById(R.id.feiLvQuery);
        feiLv = (EditText) findViewById(R.id.feiLv);
        setting = (Button) findViewById(R.id.setting);
        freeCarquery = (Button) findViewById(R.id.freeCarquery);
        freeCar = (ImageView) findViewById(R.id.freeCar);
        freeCar1 = (ImageView) findViewById(R.id.freeCar1);
    }
}
