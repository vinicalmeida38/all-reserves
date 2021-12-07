package com.example.allreserves.classes.restaurante;

import com.example.allreserves.telas.cliente.listagem.restaurantes.Restaurantes;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    public String email;
    public String senha;
    public String nome;
    public String imagem;
    public List<String> dias_funcionamento;
    public List<String> horario_funcionamento;
    public Number capacidade_maxima;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Number getCapacidade_maxima() {
        return capacidade_maxima;
    }

    public void setCapacidade_maxima(Number capacidade_maxima) {
        this.capacidade_maxima = capacidade_maxima;
    }
}
