package pl.dexbytes.forexdemo;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.jraska.livedata.TestObserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import pl.dexbytes.forexdemo.db.AppDatabase;
import pl.dexbytes.forexdemo.db.quote.QuoteDao;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.db.quote.mock.QuoteEntityMock;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DatabaseInstrumentedTest {
    private QuoteDao mQuoteDao;
    private AppDatabase mAppDatabase;

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        mAppDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(), AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        mQuoteDao = mAppDatabase.quoteDao();
    }

    @After
    public void closeDb(){
        mAppDatabase.close();
    }

    @Test
    public void testEmptyDb() throws InterruptedException {
        assertEquals(0, getQuoteTableSize());
    }

    @Test
    public void insertQuoteAndReadInList() throws InterruptedException {
        QuoteEntity quoteEntity = QuoteEntityMock.getRandomQuoteEntity();
        mQuoteDao.save(quoteEntity);
        TestObserver.test(mQuoteDao.findAll())
                .awaitValue()
                .assertHasValue()
                .assertValue(list -> list.size() == 1)
                .assertValue(list -> list.get(0).getSymbol().equals(quoteEntity.getSymbol()));
    }

    @Test
    public void insertListAndReadRows() throws InterruptedException {
        List<QuoteEntity> entities = QuoteEntityMock.getRandomQuoteEntityList(10);
        int size = getQuoteTableSize();
        mQuoteDao.saveAll(entities);
        assertEquals(size + 10, getQuoteTableSize());
    }

    @Test
    public void findAnything() throws InterruptedException {
        int size = getQuoteTableSize();
        mQuoteDao.saveAll(QuoteEntityMock.getRandomQuoteEntityList(10));
        TestObserver.test(mQuoteDao.findByName("%"))
                .awaitValue()
                .assertHasValue()
                .assertValue(list -> list.size() - size == 10);
    }

    private int getQuoteTableSize() throws InterruptedException {
        return TestObserver.test(mQuoteDao.findAll())
                .awaitValue()
                .value()
                .size();
    }
}
