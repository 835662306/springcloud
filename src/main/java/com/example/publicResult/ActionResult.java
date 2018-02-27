package com.example.publicResult;

import java.util.HashMap;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/27 11:43
 * @Version 1.0
 */
public class ActionResult extends HashMap {
    public ActionResult() {
        this.put("success", true);
    }

    public ActionResult(String msg){
        this.put("success", true);
        this.put("msg", msg);
    }

    public ActionResult(boolean isSuccess, String msg) {
        this.put("success", isSuccess);
        this.put("msg", msg);
    }

    public void setSuccess(boolean isSuccess){
        this.put("success", isSuccess);
    }
    public void setMsg(String msg){
        this.put("msg", msg);
    }
}
