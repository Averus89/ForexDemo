package pl.dexbytes.forexdemo.currencylist.main;

import android.os.Bundle;

import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.base.BaseActivity;
import pl.dexbytes.forexdemo.currencylist.currencylist.CurrencyListFragment;

public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_currency_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new CurrencyListFragment())
                    .commit();
        }
    }
}
