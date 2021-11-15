package com.victorlobato.gerenciamento.servidor.enumaration;

public enum Status {
    SERVER_UP("SERVIDOR UP"),
    SERVER_DOWN("SERVIDOR DOWN");

    private final String status;

    Status(String status) {
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
}
