package com.mgnovenniycredit.models.post.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeSolution {

    @SerializedName("to")
    @Expose
    private Integer to;
    @SerializedName("from")
    @Expose
    private Integer from;

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

}