package com.acdt.cn.its2.Utils;

import java.util.List;

public class GetParkFree {

    private List<KongXianBus> ParkFreeIdLit;

    public List<KongXianBus> getParkFreeIdLit() {
        return ParkFreeIdLit;
    }

    public void setParkFreeIdLit(List<KongXianBus> parkFreeIdLit) {
        ParkFreeIdLit = parkFreeIdLit;
    }

    @Override
    public String toString() {
        return "GetParkFree{" +
                "ParkFreeIdLit=" + ParkFreeIdLit +
                '}';
    }
}
