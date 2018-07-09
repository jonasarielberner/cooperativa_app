package com.cooperativa.presentation.cooperado.list;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooperativa.CooperativaApplication;
import com.cooperativa.R;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.presentation.cooperado.visualization.CooperadoActivity;
import com.cooperativa.presentation.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListCooperadoFragment extends Fragment implements ListCooperadoContract.View, CooperadoSummaryRecyclerViewAdapter.Listener {

    private static final String TAG = "CooperadoActivity";
    private static final int DETAIL_ACTIVITY_REQUEST = 557;


    @BindView(R.id.cooperado_recycler)
    RecyclerView recyclerViewTickets;

    @BindView(R.id.empty_placeholder_container)
    View emptyPlaceHolderContainer;

    @BindView(R.id.coordinator_list)
    CoordinatorLayout layout;


    @Inject
    ListCooperadoContract.Presenter presenter;

    private CooperadoSummaryRecyclerViewAdapter adapter;


    public ListCooperadoFragment() {
        // android mandatory constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        ((CooperativaApplication) getActivity().getApplication()).component().inject(this);

        View view = inflater.inflate( R.layout.fragment_list_cooperados, container, false);

        ButterKnife.bind(this, view);

        initAdapter(new ArrayList<>());
        return view;

    }

    private void initAdapter(List<CooperadoSummary> cooperadoSummaryList) {

        int gridViewColumns = getActivity().getResources().getInteger( R.integer.home_gridview_columns );

        GridLayoutManager layoutManager = new GridLayoutManager( getActivity(), gridViewColumns );
        recyclerViewTickets.setLayoutManager( layoutManager );
        adapter = new CooperadoSummaryRecyclerViewAdapter( cooperadoSummaryList, this );
        recyclerViewTickets.setAdapter( adapter );




    }

    @Override
    public void onResume() {
        super.onResume();
        CoopLog.d( TAG, "onResume: " );
        presenter.onViewResume( this, false );
    }

    @Override
    public void onPause() {
        super.onPause();
        CoopLog.d(TAG, "onPause: ");
        presenter.onViewPause(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroy(this);
    }

    @Override
    public void onClickCooperado(Long cooperadoId) {
        CoopLog.d(TAG, "onClickCooperado: ");
        showCooperadoScreen(cooperadoId);
    }

    @OnClick(R.id.new_cooperado)
    public void onClickNewCooperado() {
        CoopLog.d(TAG, "onClickNewCooperado: ");
        presenter.onAddNewCooperado();
    }

    @Override
    public void showCooperadoScreen(Long cooperadoId) {
        CoopLog.d(TAG, "showCooperadoScreen: ID: " + cooperadoId);

        Intent intent = new Intent(getActivity(), CooperadoActivity.class  );
        intent.putExtra( "cooperado_id", cooperadoId );
        startActivity(intent);
    }

    @Override
    public void showCooperadoList(List<CooperadoSummary> cooperadoList) {
        hideEmptyListWarn();
        adapter.removeAll();
        adapter.addAll(cooperadoList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onListEmpty() {
        showEmptyListWarn();

    }
    @Override
    public void showEmptyListWarn() {
        recyclerViewTickets.setVisibility(View.INVISIBLE);
        emptyPlaceHolderContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListNotEmpty() {
        hideEmptyListWarn();

    }
    private void hideEmptyListWarn() {
        recyclerViewTickets.setVisibility(View.VISIBLE);
        emptyPlaceHolderContainer.setVisibility(View.INVISIBLE);
    }


}

