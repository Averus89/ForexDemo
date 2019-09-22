package pl.dexbytes.forexdemo.currencylist;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

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
import pl.dexbytes.forexdemo.util.StringUtils;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_currency_list, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                mCurrencyListViewModel.setFilterText(text);
                return true;
            }
        });
        return true;
    }

    @Override
    public void onQuoteSelected(QuoteEntity quote) {
        Timber.d("QuoteDto selected: %s", quote.mSymbol);
    }
}
