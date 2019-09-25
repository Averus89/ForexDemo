package pl.dexbytes.forexdemo.currencylist.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> mSelectedPair = new MutableLiveData<>();

    @Inject
    public SharedViewModel() {
    }

    public MutableLiveData<String> getSelectedPair() {
        return mSelectedPair;
    }

    public void setSelectedPair(String selectedPair) {
        mSelectedPair.setValue(selectedPair);
    }
}
