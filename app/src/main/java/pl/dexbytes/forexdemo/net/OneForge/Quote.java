package pl.dexbytes.forexdemo.net.OneForge;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("symbol")
    String mSymbol;

    @SerializedName("price")
    Double mPrice;

    @SerializedName("bid")
    Double mBid;

    @SerializedName("ask")
    Double mAsk;

    @SerializedName("timestamp")
    Long timestamp;

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    public Double getBid() {
        return mBid;
    }

    public void setBid(Double bid) {
        mBid = bid;
    }

    public Double getAsk() {
        return mAsk;
    }

    public void setAsk(Double ask) {
        mAsk = ask;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
