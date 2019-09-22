package pl.dexbytes.forexdemo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.net.OneForge.QuoteDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuoteMapper {
    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

    QuoteDto quoteEntityToQuoteDto(QuoteEntity entity);

    QuoteEntity quoteDtoToQuoteEntity(QuoteDto dto);

    List<QuoteDto> quoteEntitiesToQuoteDtos(List<QuoteEntity> entities);

    List<QuoteEntity> quoteDtoToQuoteEntities(List<QuoteDto> dto);
}
