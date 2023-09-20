package com.example.springtest.enums;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/31 17:00
 */
public enum JoinTypeEnum {
    INNER_JOIN("INNER JOIN"),
    LEFT_JOIN("LEFT JOIN");

    String joinType;

    private JoinTypeEnum(String joinType) {
        this.joinType = joinType;
    }

    public static JoinTypeEnum getTypeByValue(String str) {
        for (JoinTypeEnum value : values()) {
            if (value.name().equals(str)) {
                return value;
            }
        }
        return null;
    }

    public String getJoinType() {
        return joinType;
    }
}