package com.cooperativa.presentation.cooperado.list;


public class CooperadoSummary {

    private String cooperadoId;
    private String name;
    private String role;
    private String description;

    public CooperadoSummary(String cooperadoId, String name, String role, String description) {
        this.cooperadoId = cooperadoId;
        this.name = name;
        this.role = role;
        this.description = description;
    }


    public String getCooperadoId(){
        return cooperadoId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "CooperadoSummary{" +
                "cooperadoId='" + cooperadoId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
