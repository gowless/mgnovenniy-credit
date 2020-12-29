package com.mgnovenniycredit.models.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Term {

    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("to")
    @Expose
    private Integer to;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

}
