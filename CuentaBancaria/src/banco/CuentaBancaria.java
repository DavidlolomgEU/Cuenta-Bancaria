package banco;

import java.util.ArrayList;
import java.util.List;

/**
 * Cuenta bancaria simple con operaciones de ingreso, retirada y consulta de
 * saldo. Proyecto: cuenta-bancaria Versión: 1.0
 */
public class CuentaBancaria {
	private String titular;
	private double saldo;
	private List<String> movimientos;

	public CuentaBancaria(String titular, double saldoInicial) {
		this.titular = titular;
		this.saldo = saldoInicial;
		this.movimientos = new ArrayList<>();
		movimientos.add("Apertura: +" + saldoInicial + " €");
	}

	public void ingresar(double cantidad) {
 if (cantidad <= 0) {
	 throw new IllegalArgumentException("La cantidad debe ser positiva");
 }
 saldo += cantidad;
 movimientos.add("Ingreso: +" + cantidad + " €");
 }

	// ¿Se puede retirar más de lo que hay?
	public void retirar(double cantidad) {
 if (cantidad <= 0) {
	 throw new IllegalArgumentException("La cantidad debe ser positiva");
 }
 saldo -= cantidad; // ← sin comprobación de saldo suficiente
 movimientos.add("Retirada: -" + cantidad + " €");
 }

	public double getSaldo() {
		return saldo;
	}

	public String getTitular() {
		return titular;
	}

	public List<String> getMovimientos() {
		return movimientos;
	}

	// Transfiere saldo a otra cuenta
	public void transferir(CuentaBancaria destino, double cantidad) {
		this.retirar(cantidad);
		destino.ingresar(cantidad);
	}
}
