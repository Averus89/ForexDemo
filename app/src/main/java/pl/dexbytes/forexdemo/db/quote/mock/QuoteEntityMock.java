package pl.dexbytes.forexdemo.db.quote.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.dexbytes.forexdemo.db.quote.QuoteEntity;

public class QuoteEntityMock {
    private static final String ALLOWED_CHARACTERS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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
        quoteEntity.setSymbol(getRandomString(6));
        quoteEntity.setAsk(random.nextDouble());
        quoteEntity.setBid(random.nextDouble());
        quoteEntity.setPrice(random.nextDouble());
        quoteEntity.setTimestamp(random.nextLong());
        return quoteEntity;
    }

    private static String getRandomString(final int sizeOfRandomString) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for(int i = 0 ; i < sizeOfRandomString ; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
}
