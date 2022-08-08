package com.example.DBS.domain;

public enum Function implements CodeValue {
    SCROLL("Sc", "스크롤"),
    PREV_PG("PP", "이전 페이지"),
    NEXT_PG("NP","다음 페이지"),
    ZOOM_IN("ZI", "확대"),
    ZOOM_OUT("ZO", "축소"),
    BACK("B", "뒤로가기"),
    SEARCH("S", "검색");
    ;

    private final String code;
    private final String value;

    Function(String code, String value) {
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
