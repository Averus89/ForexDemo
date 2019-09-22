package pl.dexbytes.forexdemo;

import org.junit.Test;

import java.util.Date;

import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.mappers.QuoteMapper;
import pl.dexbytes.forexdemo.net.OneForge.QuoteDto;

import static org.junit.Assert.assertEquals;

public class TestMappings {

    @Test
    public void testEntityToDtoMapping(){
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setSymbol("PLN");
        quoteEntity.setAsk(0.0d);
        quoteEntity.setBid(0.0d);
        quoteEntity.setPrice(0.0d);
        quoteEntity.setTimestamp(new Date().getTime());
        QuoteDto quoteDto = QuoteMapper.INSTANCE.quoteEntityToQuoteDto(quoteEntity);
        assertEquals(quoteDto.getSymbol(), quoteEntity.getSymbol());
    }

    @Test
    public void testDtoToEntityMapping(){
        QuoteDto quoteDto = new QuoteDto();
        quoteDto.setSymbol("PLN");
        quoteDto.setAsk(0.0d);
        quoteDto.setBid(0.0d);
        quoteDto.setPrice(0.0d);
        quoteDto.setTimestamp(new Date().getTime());
        QuoteEntity quoteEntity = QuoteMapper.INSTANCE.quoteDtoToQuoteEntity(quoteDto);
        assertEquals(quoteDto.getSymbol(), quoteEntity.getSymbol());
    }
}
