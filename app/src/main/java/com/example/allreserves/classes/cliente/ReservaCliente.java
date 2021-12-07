package com.example.allreserves.classes.cliente;

public class ReservaCliente extends Cliente {
    public String diaReserva;
    public String horarioReserva;
    public Number qtdPessoas;

    public String getDiaReserva() {
        return diaReserva;
    }

    public void setDiaReserva(String diaReserva) {
        this.diaReserva = diaReserva;
    }

    public String getHorarioReserva() {
        return horarioReserva;
    }

    public void setHorarioReserva(String horarioReserva) {
        this.horarioReserva = horarioReserva;
    }

    public Number getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(Number qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }
}
