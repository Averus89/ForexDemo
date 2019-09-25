package pl.dexbytes.forexdemo.db.quotehistory;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import androidx.room.Ignore;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.OffsetDateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import pl.dexbytes.forexdemo.mappers.OffsetDateTimeMapper;

public class QuoteHistoryStat {
    private double min, max, avg;
    private int day, month, year;

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @SuppressLint("DefaultLocale")
    @NotNull
    @Ignore
    public String toString(){
        return String.format("%d-%d-%d MIN: %f, AVG %f, MAX %f",
                getDay(),getMonth(), getYear(),
                getMin(), getMax(), getAvg()
        );
    }

    public CharSequence getFormattedDate(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(getYear(), getMonth() - 1, getDay(), 0,0,0);

        return DateUtils.getRelativeTimeSpanString(
                calendar.getTime().getTime(),
                new Date().getTime(),
                DateUtils.DAY_IN_MILLIS);
    }
}
