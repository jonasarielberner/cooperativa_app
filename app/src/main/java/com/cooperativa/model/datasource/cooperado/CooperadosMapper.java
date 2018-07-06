package com.cooperativa.model.datasource.cooperado;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.presentation.cooperado.list.CooperadoSummary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CooperadosMapper {

    @Inject
    public CooperadosMapper() {
        // DI Constructor
    }

    public List<CooperadoSummary> toCooperadoSummary(List<Cooperados> cooperados) {

        List<CooperadoSummary> cooperadoList = new ArrayList<>();

        for (Cooperados cooperado : cooperados) {
            cooperadoList.add(new CooperadoSummary(String.valueOf(cooperado.getId()), cooperado.getName(), cooperado.getRole(), cooperado.getDescription()));
        }

        return cooperadoList;

    }
}
