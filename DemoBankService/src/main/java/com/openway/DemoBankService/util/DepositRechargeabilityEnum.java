package com.openway.DemoBankService.util;

public enum  DepositRechargeabilityEnum {
    RECHARGEABLE (true),
    NON_RECHARGEABLE (false)
    ;

    private final boolean isRechargeable;

    private DepositRechargeabilityEnum(boolean isRechargeable){
        this.isRechargeable = isRechargeable;
    }
}
