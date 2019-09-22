package pl.dexbytes.forexdemo.currencylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.ViewHolder> {
    private CurrencyRepository.RepositorySelectedListener mRepositorySelectedListener;
    private List<QuoteEntity> mQuotes = new ArrayList<>();

    CurrencyListAdapter(CurrencyRepository.RepositorySelectedListener listener){
        mRepositorySelectedListener = listener;
        //setHasStableIds(true);
    }

    public void updateData(List<QuoteEntity> newQuotes) {
        if(mQuotes.size() > 0) {
            QuoteDiffCallback quoteDiffCallback = new QuoteDiffCallback(newQuotes, mQuotes);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(quoteDiffCallback);

            mQuotes.clear();
            mQuotes.addAll(newQuotes);
            diffResult.dispatchUpdatesTo(this);
        } else {
            mQuotes = newQuotes;
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        char[] array = mQuotes.get(position).getSymbol().toCharArray();
        int sum = IntStream.range(0,array.length).mapToObj(i -> array[i])
                .mapToInt(Integer::valueOf)
                .sum();
        return (long) sum;
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

    class QuoteDiffCallback extends DiffUtil.Callback{
        private final List<QuoteEntity> mNewQuotes, mOldQuotes;

        QuoteDiffCallback(List<QuoteEntity> newQuotes, List<QuoteEntity> oldQuotes) {
            mNewQuotes = newQuotes;
            mOldQuotes = oldQuotes;
        }

        @Override
        public int getOldListSize() {
            return mOldQuotes.size();
        }

        @Override
        public int getNewListSize() {
            return mNewQuotes.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewQuotes.get(newItemPosition).getSymbol().equals(mOldQuotes.get(oldItemPosition).getSymbol());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewQuotes.get(newItemPosition).equals(mOldQuotes.get(oldItemPosition));
        }
    }
}
