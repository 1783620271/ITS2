package com.acdt.cn.its2.ResonJson;

import com.acdt.cn.its2.Utils.BusStation;
import com.acdt.cn.its2.Utils.GetBusStation;
import com.acdt.cn.its2.Utils.GetParkFree;
import com.acdt.cn.its2.Utils.KongXianBus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Resonjson {
    /**
     * 查询当前空闲车位
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static GetParkFree ResolveGetParkFree(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONArray jsonArray = new JSONArray(serverinfo);
        GetParkFree getParkFree = new GetParkFree();
        List<KongXianBus> parkFreeIdList=new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            KongXianBus parkFreeId = new KongXianBus();
            parkFreeId.setBuwei(jsonArray.getJSONObject(i).getInt("ParkFreeId"));
            parkFreeIdList.add(parkFreeId);
        }
        getParkFree.setParkFreeIdLit(parkFreeIdList);
        return getParkFree;
    }
    /**
     * 获取到站台的距离
     */
    public static GetBusStation ResolveGetBusStation(String jsonStr) throws JSONException {
        //Log.i(TAG, "ResolveGetBusStation: "+11111111);
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONArray jsonArray = new JSONArray(serverinfo);
        GetBusStation getBusStation=new GetBusStation();
        List<BusStation> BusTionList=new ArrayList<BusStation>();
        for (int i = 0; i < jsonArray.length(); i++) {
            // Log.i(TAG, "ResolveGetBusStation: "+2222222);
            BusStation busStation = new BusStation();
            busStation.setBusId(jsonArray.getJSONObject(i).getInt("BusId"));
            // Log.i(TAG, "ResolveGetBusStation: "+jsonArray.getJSONObject(i).getInt("BusId"));
            busStation.setDistance(jsonArray.getJSONObject(i).getInt("Distance"));
            //Log.i(TAG, "ResolveGetBusStation: "+jsonArray.getJSONObject(i).getInt("Distance"));
            BusTionList.add(busStation);
        }
        getBusStation.setBusTionList(BusTionList);
        //  Log.i(TAG, "ResolveGetBusStation: 111"+getBusStation.getBusTionList().get(0).toString());
        return getBusStation;
    }
}
