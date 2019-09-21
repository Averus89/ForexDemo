package pl.dexbytes.forexdemo.currencylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.net.ApiResponse;
import pl.dexbytes.forexdemo.net.OneForge.Quote;
import timber.log.Timber;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.ViewHolder> {
    private CurrencyRepository.RepositorySelectedListener mRepositorySelectedListener;
    private final List<Quote> mQuotes = new ArrayList<>();

    CurrencyListAdapter(CurrencyListViewModel viewModel, LifecycleOwner lifecycleOwner, CurrencyRepository.RepositorySelectedListener listener){
        mRepositorySelectedListener = listener;
        viewModel.refresh().observe(lifecycleOwner, apiResponse -> {
            mQuotes.clear();
            consumeResponse(apiResponse);
            notifyDataSetChanged();
        });
        setHasStableIds(true);
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.getStatus()){
            case LOADING:
                Timber.d("loading");
                break;
            case SUCCESS:
                Timber.d("success");
                renderSuccessResponse(apiResponse);
                break;
            case ERROR:
                Timber.e(apiResponse.getError());
                break;
        }
    }

    private void renderSuccessResponse(ApiResponse apiResponse) {
        if (apiResponse.getResult() instanceof List) {
            ((List) apiResponse.getResult()).stream()
                    .filter(o -> o instanceof Quote)
                    .forEach(q -> mQuotes.add((Quote) q));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_currenty_list_item, parent, false);
        return new ViewHolder(view, mRepositorySelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mQuotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuotes.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.currencyPair) TextView mCurrencyPair;
        @BindView(R.id.bid) TextView mBid;
        @BindView(R.id.ask) TextView mAsk;
        @BindView(R.id.price) TextView mPrice;
        Quote mQuote;

        public ViewHolder(@NonNull View itemView, CurrencyRepository.RepositorySelectedListener repositorySelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(mQuote != null) {
                    repositorySelectedListener.onQuoteSelected(mQuote);
                }
            });
        }

        void bind(Quote quote){
            mQuote = quote;
            mCurrencyPair.setText(quote.getSymbol());
            mAsk.setText(String.valueOf(quote.getAsk()));
            mBid.setText(String.valueOf(quote.getBid()));
            mPrice.setText(String.valueOf(quote.getPrice()));
        }
    }
}
