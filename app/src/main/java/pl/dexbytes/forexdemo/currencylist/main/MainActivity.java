package pl.dexbytes.forexdemo.currencylist.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.base.BaseActivity;
import pl.dexbytes.forexdemo.currencylist.currencylist.CurrencyListFragment;
import pl.dexbytes.forexdemo.util.ViewModelFactory;

public class MainActivity extends BaseActivity {
    @Inject
    ViewModelFactory mViewModelFactory;
    private SharedViewModel mSharedViewModel;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedViewModel = ViewModelProviders
                .of(this, mViewModelFactory)
                .get(SharedViewModel.class);
        mSharedViewModel.getSelectedPair().observe(this, this::setTitle);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new CurrencyListFragment())
                    .commit();
        }
    }
}
