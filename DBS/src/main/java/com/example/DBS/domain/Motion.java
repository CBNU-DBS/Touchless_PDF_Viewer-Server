package com.example.DBS.domain;

public enum Motion implements CodeValue{
    UP("U", "고개 위로"),
    DOWN("D", "고개 아래로"),
    LEFT("L", "고개 왼쪽으로"),
    RIGHT("R", "고개 왼쪽으로"),
    LEFT_WINK("LW", "왼쪽 눈 감기"),
    RIGHT_WINK("RW", "오른쪽 눈 감기"),
    BOTH_WINK("BW", "양쪽 눈 감기");

    private final String code;
    private final String value;

    Motion(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
