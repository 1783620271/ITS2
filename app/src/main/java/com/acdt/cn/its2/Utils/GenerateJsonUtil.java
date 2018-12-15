package com.acdt.cn.its2.Utils;

public class GenerateJsonUtil {

    /**
     * 站台
     */
    public static String GenerateGetBusStationinfo(String BusStationId){
        String jsonStr="{\"BusStationId\":"+BusStationId+"}";
        return jsonStr;
    }
    public static String GenerateGetTrafficLightConfigAction(Integer TrafficLightId){
        String jsonStr="{\"TrafficLightId\":"+TrafficLightId+"}";
        return jsonStr;
    }
}
