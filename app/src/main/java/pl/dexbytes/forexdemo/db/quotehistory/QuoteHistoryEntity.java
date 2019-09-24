package pl.dexbytes.forexdemo.db.quotehistory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.threeten.bp.OffsetDateTime;

import pl.dexbytes.forexdemo.db.util.TimeStampConverter;

@Entity(tableName = "quote_history")
public class QuoteHistoryEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int mId;

    @ColumnInfo(name = "symbol")
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
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
