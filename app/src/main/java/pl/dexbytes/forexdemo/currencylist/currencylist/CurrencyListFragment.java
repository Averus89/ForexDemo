package pl.dexbytes.forexdemo.currencylist.currencylist;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.base.BaseFragment;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.util.ViewModelFactory;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrencyListFragment extends BaseFragment implements CurrencyRepository.RepositorySelectedListener {
    @BindView(R.id.recyclerView)
    RecyclerView listView;

    @Inject
    ViewModelFactory mViewModelFactory;
    private CurrencyListViewModel mCurrencyListViewModel;

    public CurrencyListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_currency_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mCurrencyListViewModel = ViewModelProviders
                .of(this, mViewModelFactory)
                .get(CurrencyListViewModel.class);

        final CurrencyListAdapter adapter = new CurrencyListAdapter(this);
        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));

        mCurrencyListViewModel.getQuotes().observe(this, adapter::updateData);
    }

    @Override
    public void onQuoteSelected(QuoteEntity quote) {
        Timber.d("QuoteDto selected: %s", quote.mSymbol);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.activity_currency_list, menu);
        SearchManager searchManager = (SearchManager) getBaseActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getBaseActivity().getComponentName()));
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
        super.onCreateOptionsMenu(menu, inflater);
    }

}