package pl.dexbytes.forexdemo.currencylist.history;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.base.BaseFragment;
import pl.dexbytes.forexdemo.currencylist.main.SharedViewModel;
import pl.dexbytes.forexdemo.util.ViewModelFactory;

public class CurrencyHistoryFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Inject
    ViewModelFactory mViewModelFactory;
    private SharedViewModel mSharedViewModel;
    private CurrencyHistoryViewModel mHistoryViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHistoryViewModel = ViewModelProviders
                .of(this, mViewModelFactory)
                .get(CurrencyHistoryViewModel.class);
        mSharedViewModel = ViewModelProviders
                .of(getBaseActivity(), mViewModelFactory)
                .get(SharedViewModel.class);
        mHistoryViewModel.restoreFromBundle(savedInstanceState);

        CurrencyHistoryAdapter currencyHistoryAdapter = new CurrencyHistoryAdapter(getBaseActivity());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(currencyHistoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));

        mHistoryViewModel.getStatsForPair(mSharedViewModel.getSelectedPair()).observe(this, currencyHistoryAdapter::updateData);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mHistoryViewModel.saveToBundle(outState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_simple_list;
    }
}
