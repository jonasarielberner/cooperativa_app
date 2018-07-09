package com.cooperativa.presentation.cooperado.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cooperativa.R;
import com.cooperativa.model.datasource.logging.CoopLog;

import java.util.List;

public class CooperadoSummaryRecyclerViewAdapter extends RecyclerView.Adapter<CooperadoSummaryRecyclerViewAdapter.CooperadoSummaryHolder> {

    private static final String TAG = "CooperadoRecyclerViewAdapt";

    private final List<CooperadoSummary> mValues;

    private Listener listener;

    private Long cooperadoId;

    public CooperadoSummaryRecyclerViewAdapter(List<CooperadoSummary> items, Listener listener) {
        mValues = items;
        this.listener = listener;
    }



    @Override
    public CooperadoSummaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cooperado_list_item, parent, false);
        return new CooperadoSummaryHolder(view);
    }

    @Override
    public void onBindViewHolder(final CooperadoSummaryHolder holder, int position) {
        CoopLog.d(TAG, "onBindViewHolder: ");

        CooperadoSummary cooperadoSummary = mValues.get(position);

        holder.mItem = cooperadoSummary;
        holder.titleView.setText(cooperadoSummary.getName());
        holder.subtitleView.setText(cooperadoSummary.getRole());

        View containerView = holder.mView;

        containerView.setOnClickListener(v -> {
            if (listener != null) {
                CoopLog.i(TAG, "onClick: " + holder.mItem);
                cooperadoId = Long.parseLong( cooperadoSummary.getCooperadoId());
                listener.onClickCooperado(cooperadoId);
            } else {
                CoopLog.i(TAG, "onClick: " + holder.mItem);
            }
        });
        adjustMargins(position, containerView);
    }
    private void adjustMargins(int position, View containerView) {
        ViewGroup.LayoutParams layoutParams = containerView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);

        int cardMargin = (int) containerView.getContext().getResources().getDimension(R.dimen.card_margin);
        marginLayoutParams.bottomMargin = cardMargin;
        marginLayoutParams.topMargin = cardMargin;
        marginLayoutParams.rightMargin = cardMargin;
        marginLayoutParams.leftMargin = cardMargin;

        if (position != mValues.size() - 1) {
            marginLayoutParams.bottomMargin = 0;
        }


        containerView.setLayoutParams(marginLayoutParams);
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addAll(List<CooperadoSummary> cooperadoList) {
        mValues.addAll(cooperadoList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mValues.clear();
        notifyDataSetChanged();
    }

    public CooperadoSummary getItemAt(int adapterPosition) {
        return mValues.get(adapterPosition);
    }

    public void removeAt(int position) {
        mValues.remove(position);
        notifyItemRemoved(position);
        notifyViewListSizeChanged();
    }


    private void notifyViewListSizeChanged() {
        if (mValues.isEmpty()) {
            listener.onListEmpty();
        } else {
            listener.onListNotEmpty();
        }
    }

    public void insertAt(int position, CooperadoSummary cooperadoSummary) {
        mValues.add(position, cooperadoSummary);
        notifyItemInserted(position);
        notifyViewListSizeChanged();
    }





    public interface Listener {
        void onClickCooperado(Long cooperadoId);

        void onListEmpty();

        void onListNotEmpty();
    }

    public class CooperadoSummaryHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView titleView;
        final TextView subtitleView;
        CooperadoSummary mItem;

        CooperadoSummaryHolder(View view) {
            super( view );
            mView = view;
            titleView = view.findViewById( R.id.text_view_cooperado_nome );
            subtitleView = view.findViewById( R.id.text_view_cooperado_role );
        }
    }

}
