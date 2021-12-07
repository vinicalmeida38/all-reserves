package com.example.allreserves.classes.cliente;

public class ListaReservaCliente {
    public String nomeRestaurante;
    public String horarioReserva;

    public ListaReservaCliente(String nomeRestaurante, String horarioReserva){
        this.nomeRestaurante = nomeRestaurante;
        this.horarioReserva = horarioReserva;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public String getHorarioReserva() {
        return horarioReserva;
    }

    public void setHorarioReserva(String horarioReserva) {
        this.horarioReserva = horarioReserva;
    }
}
