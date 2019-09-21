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
import pl.dexbytes.forexdemo.di.CurrencyListActivityScope;
import pl.dexbytes.forexdemo.net.ApiResponse;
import pl.dexbytes.forexdemo.net.OneForge.Quote;
import timber.log.Timber;

public class CurrencyListActivity extends BaseActivity implements CurrencyRepository.RepositorySelectedListener {
    @Inject
    @CurrencyListActivityScope
    CurrencyListViewModelFactory mCurrencyListViewModelFactory;
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
                .of(this, mCurrencyListViewModelFactory)
                .get(CurrencyListViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listView.setAdapter(new CurrencyListAdapter(mCurrencyListViewModel, this, this));
        listView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onQuoteSelected(Quote quote) {
        Timber.d("Quote selected: %s", quote.getSymbol());
    }
}
