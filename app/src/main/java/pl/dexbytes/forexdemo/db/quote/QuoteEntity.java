package pl.dexbytes.forexdemo.db.quote;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.threeten.bp.OffsetDateTime;

import pl.dexbytes.forexdemo.db.util.TimeStampConverter;

@Entity(tableName = "quote")
public class QuoteEntity {
    @PrimaryKey
    @ColumnInfo(name = "uid")
    @NonNull
    public String mSymbol;

    @ColumnInfo(name ="bid")
    public double mBid;

    @ColumnInfo(name = "ask")
    public double mAsk;

    @ColumnInfo(name = "price")
    public double mPrice;

    @ColumnInfo(name = "timestamp")
    @TypeConverters({TimeStampConverter.class})
    public OffsetDateTime mDateTime;

    @NonNull
    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(@NonNull String symbol) {
        mSymbol = symbol;
    }

    public double getBid() {
        return mBid;
    }

    public void setBid(double bid) {
        mBid = bid;
    }

    public double getAsk() {
        return mAsk;
    }

    public void setAsk(double ask) {
        mAsk = ask;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public OffsetDateTime getDateTime() {
        return mDateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        mDateTime = dateTime;
    }
}
