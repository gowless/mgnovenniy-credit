package com.mgnovenniycredit.models.post.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Liste {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("cpa")
    @Expose
    private String cpa;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("top")
    @Expose
    private Boolean top;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("term")
    @Expose
    private Term term;
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("detail")
    @Expose
    private Detail detail;
    @SerializedName("percent")
    @Expose
    private Percent percent;
    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("categories")
    @Expose
    private java.util.List<String> categories;
    @SerializedName("offer_name")
    @Expose
    private String offerName;
    @SerializedName("time_solution")
    @Expose
    private TimeSolution timeSolution;
    @SerializedName("push")
    @Expose
    private Push push;
    @SerializedName("isHidden")
    @Expose
    private Boolean isHidden;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpa() {
        return cpa;
    }

    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Percent getPercent() {
        return percent;
    }

    public void setPercent(Percent percent) {
        this.percent = percent;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public java.util.List<String> getCategories() {
        return categories;
    }

    public void setCategories(java.util.List<String> categories) {
        this.categories = categories;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public TimeSolution getTimeSolution() {
        return timeSolution;
    }

    public void setTimeSolution(TimeSolution timeSolution) {
        this.timeSolution = timeSolution;
    }

    public Push getPush() {
        return push;
    }

    public void setPush(Push push) {
        this.push = push;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

}
