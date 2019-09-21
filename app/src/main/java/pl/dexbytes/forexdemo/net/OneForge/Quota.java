package pl.dexbytes.forexdemo.net.OneForge;

import com.google.gson.annotations.SerializedName;

class Quota {
    @SerializedName("quota_used")
    Integer mQuotaUsed;

    @SerializedName("quota_limit")
    Integer mQuotaLimit;

    @SerializedName("quota_remaining")
    Integer mQuotaRemaining;

    @SerializedName("hours_until_reset")
    Integer mHoursUntilReset;

    public Integer getQuotaUsed() {
        return mQuotaUsed;
    }

    public void setQuotaUsed(Integer quotaUsed) {
        mQuotaUsed = quotaUsed;
    }

    public Integer getQuotaLimit() {
        return mQuotaLimit;
    }

    public void setQuotaLimit(Integer quotaLimit) {
        mQuotaLimit = quotaLimit;
    }

    public Integer getQuotaRemaining() {
        return mQuotaRemaining;
    }

    public void setQuotaRemaining(Integer quotaRemaining) {
        mQuotaRemaining = quotaRemaining;
    }

    public Integer getHoursUntilReset() {
        return mHoursUntilReset;
    }

    public void setHoursUntilReset(Integer hoursUntilReset) {
        mHoursUntilReset = hoursUntilReset;
    }
}
