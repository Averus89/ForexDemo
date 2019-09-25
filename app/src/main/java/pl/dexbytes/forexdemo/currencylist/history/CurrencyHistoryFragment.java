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
import pl.dexbytes.forexdemo.util.ViewModelFactory;

public class CurrencyHistoryFragment extends BaseFragment {
    private static final String ARG_PAIR = "arg_pair";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Inject
    ViewModelFactory mViewModelFactory;
    private CurrencyHistoryViewModel mHistoryViewModel;
    private String mPair;

    public static CurrencyHistoryFragment newInstance(String pair) {
        Bundle args = new Bundle();
        args.putString(ARG_PAIR, pair);

        CurrencyHistoryFragment fragment = new CurrencyHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mPair = getArguments().getString(ARG_PAIR);
        } else {
            throw new IllegalArgumentException("Fragment must have pair initialized");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHistoryViewModel = ViewModelProviders
                .of(this, mViewModelFactory)
                .get(CurrencyHistoryViewModel.class);
        mHistoryViewModel.restoreFromBundle(savedInstanceState);

        CurrencyHistoryAdapter currencyHistoryAdapter = new CurrencyHistoryAdapter(getBaseActivity());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(currencyHistoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));

        mHistoryViewModel.getStatsForPair().observe(this, currencyHistoryAdapter::updateData);
        mHistoryViewModel.setSelectedPair(mPair);
        getBaseActivity().setTitle(mPair);
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
