package pl.dexbytes.forexdemo.currencylist.history;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryDao;
import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryStat;

public class CurrencyHistoryRepository {
    private final QuoteHistoryDao mQuoteHistoryDao;

    @Inject
    public CurrencyHistoryRepository(QuoteHistoryDao quoteHistoryDao) {
        mQuoteHistoryDao = quoteHistoryDao;
    }

    public LiveData<List<QuoteHistoryStat>> getStatsForPair(String pair){
        return mQuoteHistoryDao.selectStatsForPair(pair);
    }
}
