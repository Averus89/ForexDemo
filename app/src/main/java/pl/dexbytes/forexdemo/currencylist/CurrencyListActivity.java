package pl.dexbytes.forexdemo.currencylist;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import pl.dexbytes.forexdemo.base.BaseActivity;
import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.util.ViewModelFactory;
import timber.log.Timber;

public class CurrencyListActivity extends BaseActivity implements CurrencyRepository.RepositorySelectedListener {
    @Inject
    ViewModelFactory mViewModelFactory;
    CurrencyListViewModel mCurrencyListViewModel;
    @BindView(R.id.recyclerView)
    RecyclerView listView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_currency_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        mCurrencyListViewModel = ViewModelProviders
                .of(this, mViewModelFactory)
                .get(CurrencyListViewModel.class);

        final CurrencyListAdapter adapter = new CurrencyListAdapter(this);
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        mCurrencyListViewModel.getQuotes().observe(this, adapter::updateData);
    }

    @Override
    public void onQuoteSelected(QuoteEntity quote) {
        Timber.d("QuoteDto selected: %s", quote.mSymbol);
    }
}
