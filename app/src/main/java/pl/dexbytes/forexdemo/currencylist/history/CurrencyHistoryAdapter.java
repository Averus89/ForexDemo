package pl.dexbytes.forexdemo.currencylist.history;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryStat;
import timber.log.Timber;

public class CurrencyHistoryAdapter extends RecyclerView.Adapter<CurrencyHistoryAdapter.ViewHolder> {
    private List<QuoteHistoryStat> mStats = new ArrayList<>();
    private Context mContext;

    public CurrencyHistoryAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_currency_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mStats.get(position));
    }

    @Override
    public int getItemCount() {
        return mStats.size();
    }

    @Override
    public long getItemId(int position) {
        QuoteHistoryStat stat = mStats.get(position);
        return stat.getDay() + stat.getMonth() + stat.getYear();
    }

    void updateData(List<QuoteHistoryStat> quoteHistoryStats) {
        Timber.d("Data updated");
        if(quoteHistoryStats.size() > 0) {
            CurrencyHistoryAdapter.DiffCallback diffCallback = new CurrencyHistoryAdapter.DiffCallback(quoteHistoryStats, mStats);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            mStats.clear();
            mStats.addAll(quoteHistoryStats);
            diffResult.dispatchUpdatesTo(this);
        } else {
            mStats = quoteHistoryStats;
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.date) TextView mDate;
        @BindView(R.id.minPrice) TextView mMinPrice;
        @BindView(R.id.maxPrice) TextView mMaxPrice;
        @BindView(R.id.avgPrice) TextView mAvgPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("DefaultLocale")
        public void bind(QuoteHistoryStat stat) {
            mDate.setText(stat.getFormattedDate());
            mMinPrice.setText(String.valueOf(stat.getMin()));
            mMaxPrice.setText(String.valueOf(stat.getMax()));
            mAvgPrice.setText(String.valueOf(stat.getAvg()));
        }
    }

    class DiffCallback extends DiffUtil.Callback{
        private final List<QuoteHistoryStat> mNewQuotes, mOldQuotes;

        DiffCallback(List<QuoteHistoryStat> newQuotes, List<QuoteHistoryStat> oldQuotes) {
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
            return mNewQuotes.get(newItemPosition).getDay() == mOldQuotes.get(oldItemPosition).getDay() &&
                    mNewQuotes.get(newItemPosition).getMonth() == mOldQuotes.get(oldItemPosition).getMonth() &&
                    mNewQuotes.get(newItemPosition).getYear() == mOldQuotes.get(oldItemPosition).getYear();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewQuotes.get(newItemPosition).equals(mOldQuotes.get(oldItemPosition));
        }
    }
}
