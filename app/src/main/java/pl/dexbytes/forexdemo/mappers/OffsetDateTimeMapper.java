package pl.dexbytes.forexdemo.mappers;

import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.temporal.ChronoField;

import pl.dexbytes.forexdemo.db.util.TimeStampConverter;

public class OffsetDateTimeMapper {
    public Long asLong(OffsetDateTime dateTime){
        return dateTime.getLong(ChronoField.INSTANT_SECONDS);
    }

    public OffsetDateTime fromLong(long timestamp){
        Instant instant = Instant.ofEpochSecond(timestamp);
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    public static OffsetDateTime fromString(String timestamp){
        return TimeStampConverter.dateFromTimestamp(timestamp);
    }

    public static String asString(OffsetDateTime date){
        return TimeStampConverter.timestampFromDate(date);
    }
}
