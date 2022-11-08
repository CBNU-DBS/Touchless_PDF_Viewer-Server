package com.example.DBS.domain;

/**
 * 성공 여부만 반환하는 공통 ResponseBody 클래스
 * resultCode == 0 : 성공, 음수 : 실패
 * resultMsg == 에러 메시지
 */
public class BaseResponseBody {
    protected int resultCode;
    protected String resultMsg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public BaseResponseBody() {
    }

    public BaseResponseBody(String resultMsg) {
        this.resultCode = 0;
        this.resultMsg = resultMsg;
    }
}
