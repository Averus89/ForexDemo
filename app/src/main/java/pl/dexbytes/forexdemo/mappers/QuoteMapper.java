package pl.dexbytes.forexdemo.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryDao;
import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryEntity;
import pl.dexbytes.forexdemo.net.OneForge.QuoteDto;

@Mapper(uses = {OffsetDateTimeMapper.class})
public interface QuoteMapper {
    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

    @Mapping(target = "timestamp", source = "mDateTime")
    QuoteDto quoteEntityToQuoteDto(QuoteEntity entity);

    @InheritInverseConfiguration
    QuoteEntity quoteDtoToQuoteEntity(QuoteDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mDateTime", source = "timestamp")
    QuoteHistoryEntity quoteDtoToQuoteHistoryEntity(QuoteDto dto);

    @InheritInverseConfiguration
    QuoteDto quoteHistoryEntityToQuoteDto(QuoteHistoryEntity quoteHistoryEntity);

    List<QuoteDto> quoteEntitiesToQuoteDtos(List<QuoteEntity> entities);

    List<QuoteEntity> quoteDtoToQuoteEntities(List<QuoteDto> dto);

    List<QuoteHistoryEntity> quoteDtoToQuoteHistoryEntities(List<QuoteDto> dto);
}
