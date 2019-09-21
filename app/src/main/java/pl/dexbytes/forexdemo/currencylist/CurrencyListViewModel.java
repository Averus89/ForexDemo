package pl.dexbytes.forexdemo.currencylist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import pl.dexbytes.forexdemo.net.ApiResponse;
import pl.dexbytes.forexdemo.rx.SchedulersFacade;

public class CurrencyListViewModel extends ViewModel {
    private final SchedulersFacade mSchedulersFacade;
    private final CurrencyRepository mCurrencyRepository;
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> mResponseLiveData = new MutableLiveData<>();


    public CurrencyListViewModel(SchedulersFacade schedulersFacade, CurrencyRepository currencyRepository) {
        mSchedulersFacade = schedulersFacade;
        mCurrencyRepository = currencyRepository;
        refreshSampleData();
    }

    public MutableLiveData<ApiResponse> refresh(){
        return mResponseLiveData;
    }

    public void refreshSampleData(){
        mDisposables.add(mCurrencyRepository.getQuotes()
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .doOnSubscribe(d -> mResponseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> mResponseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> mResponseLiveData.setValue(ApiResponse.error(throwable))
                )
        );
    }

    @Override
    protected void onCleared() {
        mDisposables.clear();
    }
}
