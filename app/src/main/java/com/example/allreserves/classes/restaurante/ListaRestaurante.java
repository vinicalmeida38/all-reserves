package com.example.allreserves.classes.restaurante;

import java.util.ArrayList;
import java.util.List;

public class ListaRestaurante {
    public String nome;
    public String imagem;
    public Number capacidade_max;
    public List<String> dias_funcionamento;
    public List<String> horario_funcionamento;
    public String uid;

    public ListaRestaurante(String nome, String imagem, Number capacidade_max, List<String> dias_funcionamento, List<String> horario_funcionamento, String uid){
        this.nome = nome;
        this.imagem = imagem;
        this.capacidade_max = capacidade_max;
        this.dias_funcionamento = dias_funcionamento;
        this.horario_funcionamento = horario_funcionamento;
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Number getCapacidade_max() {
        return capacidade_max;
    }

    public void setCapacidade_max(Number capacidade_max) {
        this.capacidade_max = capacidade_max;
    }

    public List<String> getDias_funcionamento() {
        return dias_funcionamento;
    }

    public void setDias_funcionamento(List<String> dias_funcionamento) {
        this.dias_funcionamento = dias_funcionamento;
    }

    public List<String> getHorario_funcionamento() {
        return horario_funcionamento;
    }

    public void setHorario_funcionamento(List<String> horario_funcionamento) {
        this.horario_funcionamento = horario_funcionamento;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
