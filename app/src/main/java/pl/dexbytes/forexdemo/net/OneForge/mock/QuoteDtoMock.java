package pl.dexbytes.forexdemo.net.OneForge.mock;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import pl.dexbytes.forexdemo.db.quote.mock.QuoteEntityMock;
import pl.dexbytes.forexdemo.net.OneForge.QuoteDto;

import static pl.dexbytes.forexdemo.util.StringUtils.getRandomString;

public class QuoteDtoMock {
    public static QuoteDto getRandomQuoteDto() {
        Random random = new Random();
        QuoteDto quoteDto = new QuoteDto();
        quoteDto.setSymbol(getRandomString(6, QuoteEntityMock.ALLOWED_CHARACTERS));
        quoteDto.setAsk(random.nextDouble());
        quoteDto.setBid(random.nextDouble());
        quoteDto.setPrice(random.nextDouble());
        quoteDto.setTimestamp(TimeUnit.SECONDS.convert(new Date().getTime(), TimeUnit.MILLISECONDS));
        return quoteDto;
    }
}
