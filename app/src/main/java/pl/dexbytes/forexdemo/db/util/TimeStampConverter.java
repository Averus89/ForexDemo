package pl.dexbytes.forexdemo.db.util;

import androidx.room.TypeConverter;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import pl.dexbytes.forexdemo.util.StringUtils;

public class TimeStampConverter {
    private static DateTimeFormatter FORMAT = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @TypeConverter
    public static OffsetDateTime dateFromTimestamp(String timestamp){
        if(StringUtils.isNotEmpty(timestamp)){
            try {
                return FORMAT.parse(timestamp, OffsetDateTime.FROM);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String timestampFromDate(OffsetDateTime date){
        return date != null ? date.format(FORMAT) : "";
    }
}
