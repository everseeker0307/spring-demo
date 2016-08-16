package com.everseeker.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by everseeker on 16/8/16.
 */
public class ValidatorRest implements Serializable {
    //返回给前端的结果, true表示成功, false表示失败
    private boolean result;

    //如果rest请求失败, 返回错误的具体内容
    private Map error = null;

    //请求成功, 返回数据
    private Object data;

    public ValidatorRest() {}

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public Map getError() {
        return error;
    }

    public void setError(Map error) {
        if (error instanceof HashMap) {
            HashMap hashMap = (HashMap) error;
            this.error = (Map)hashMap.clone();
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
