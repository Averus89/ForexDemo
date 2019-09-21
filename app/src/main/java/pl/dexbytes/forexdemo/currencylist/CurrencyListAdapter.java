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
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.ViewHolder> {
    private CurrencyRepository.RepositorySelectedListener mRepositorySelectedListener;
    private final List<QuoteEntity> mQuotes = new ArrayList<>();

    CurrencyListAdapter(CurrencyListViewModel viewModel, LifecycleOwner lifecycleOwner, CurrencyRepository.RepositorySelectedListener listener){
        mRepositorySelectedListener = listener;
        viewModel.getAllQuotes().observe(lifecycleOwner, quoteEntities -> {
            mQuotes.clear();
            mQuotes.addAll(quoteEntities);
            notifyDataSetChanged();
        });
        setHasStableIds(true);
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
        QuoteEntity mQuote;

        public ViewHolder(@NonNull View itemView, CurrencyRepository.RepositorySelectedListener repositorySelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(mQuote != null) {
                    repositorySelectedListener.onQuoteSelected(mQuote);
                }
            });
        }

        void bind(QuoteEntity quote){
            mQuote = quote;
            mCurrencyPair.setText(quote.getSymbol());
            mAsk.setText(String.valueOf(quote.getAsk()));
            mBid.setText(String.valueOf(quote.getBid()));
            mPrice.setText(String.valueOf(quote.getPrice()));
        }
    }
}
