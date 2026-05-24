package banco;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {
    private String titular;
    private double saldo;
    private List<String> movimientos;

    public CuentaBancaria(String titular, double saldoInicial) {
        // FIX IR-004: Validar saldo inicial
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.titular = titular;
        this.saldo = saldoInicial;
        this.movimientos = new ArrayList<>();
        this.movimientos.add("Apertura: +" + saldoInicial + " €");
    }

    public void ingresar(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        this.saldo += cantidad;
        this.movimientos.add("Ingreso: +" + cantidad + " €");
    }

    public void retirar(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        // FIX IR-001 e IR-003: Comprobar que hay saldo suficiente
        if (cantidad > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo -= cantidad; 
        this.movimientos.add("Retirada: -" + cantidad + " €");
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String getTitular() {
        return this.titular;
    }

    public List<String> getMovimientos() {
        return this.movimientos;
    }

    public void transferir(CuentaBancaria destino, double cantidad) {
        // FIX IR-002: Comprobar que el destino no es nulo
        if (destino == null) {
            throw new IllegalArgumentException("La cuenta destino no puede ser nula");
        }
        this.retirar(cantidad);
        destino.ingresar(cantidad);
    }
}