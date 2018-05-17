package com.christiandstavares.helpdesk.response;

import java.util.ArrayList;
import java.util.List;

public class Resposta<T> {

    private T dados;
    private List<String> erros;

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public List<String> getErros() {
        if (erros == null) {
            erros = new ArrayList<>();
        }
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
