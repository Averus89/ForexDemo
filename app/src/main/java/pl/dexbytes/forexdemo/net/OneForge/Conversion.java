package pl.dexbytes.forexdemo.net.OneForge;

import com.google.gson.annotations.SerializedName;

class Conversion {
    @SerializedName("value")
    Double mValue;

    @SerializedName("text")
    String mText;

    @SerializedName("mTimestamp")
    Long mTimestamp;

    public Double getValue() {
        return mValue;
    }

    public void setValue(Double value) {
        mValue = value;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Long timestamp) {
        mTimestamp = timestamp;
    }
}
