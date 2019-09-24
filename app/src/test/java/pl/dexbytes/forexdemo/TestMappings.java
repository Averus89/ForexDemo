package pl.dexbytes.forexdemo;

import org.junit.Test;

import java.util.Date;

import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.db.quote.mock.QuoteEntityMock;
import pl.dexbytes.forexdemo.mappers.QuoteMapper;
import pl.dexbytes.forexdemo.net.OneForge.QuoteDto;
import pl.dexbytes.forexdemo.net.OneForge.mock.QuoteDtoMock;

import static org.junit.Assert.assertEquals;

public class TestMappings {

    @Test
    public void testEntityToDtoMapping(){
        QuoteEntity quoteEntity = QuoteEntityMock.getRandomQuoteEntity();
        QuoteDto quoteDto = QuoteMapper.INSTANCE.quoteEntityToQuoteDto(quoteEntity);
        assertEquals(quoteDto.getSymbol(), quoteEntity.getSymbol());
    }

    @Test
    public void testDtoToEntityMapping(){
        QuoteDto quoteDto = QuoteDtoMock.getRandomQuoteDto();
        QuoteEntity quoteEntity = QuoteMapper.INSTANCE.quoteDtoToQuoteEntity(quoteDto);
        assertEquals(quoteDto.getSymbol(), quoteEntity.getSymbol());
    }
}
