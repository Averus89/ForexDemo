package pl.dexbytes.forexdemo.db.quote.mock;

import com.anychart.scales.DateTime;

import org.threeten.bp.Instant;
import org.threeten.bp.ZoneOffset;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.util.StringUtils;

public class QuoteEntityMock {
    public static final String ALLOWED_CHARACTERS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static List<QuoteEntity> getRandomQuoteEntityList(final int size){
        List<QuoteEntity> entities = new ArrayList<>();
        for(int i  = 0; i < size ; i++){
            entities.add(getRandomQuoteEntity());
        }
        return entities;
    }

    public static QuoteEntity getRandomQuoteEntity(){
        Random random = new Random();
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setSymbol(StringUtils.getRandomString(6, ALLOWED_CHARACTERS));
        quoteEntity.setAsk(random.nextDouble());
        quoteEntity.setBid(random.nextDouble());
        quoteEntity.setPrice(random.nextDouble());
        quoteEntity.setDateTime(Instant.ofEpochMilli(new Date().getTime()).atOffset(ZoneOffset.ofHours(random.nextInt(12))));
        return quoteEntity;
    }

}
