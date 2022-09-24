package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public  class CustomResponseBody<T> extends BaseResponseBody{
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public CustomResponseBody(){
        list = new ArrayList<>();
    }

    public CustomResponseBody(String resultMsg) {
        this.resultCode = 0;
        this.resultMsg = resultMsg;
        this.list = new ArrayList<>();
    }

    public CustomResponseBody(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.list = new ArrayList<>();
    }

    public CustomResponseBody(int resultCode, String resultMsg, List<T> list) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.list = list;
    }
}
