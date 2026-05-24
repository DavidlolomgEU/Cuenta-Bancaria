package banco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CuentaBancariaTest {

    @Test
    void TC001_ingresarCantidadPositiva() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 100.0);
        cuenta.ingresar(50.0);
        assertEquals(150.0, cuenta.getSaldo());
    }

    @Test
    void TC002_retirarCantidadNegativa() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 100.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(-10.0));
        assertEquals("La cantidad debe ser positiva", exception.getMessage());
    }

    @Test
    void TC003_retirarMasDelSaldoDisponible() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 50.0);
        assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(100.0));
    }

    @Test
    void TC004_transferenciaValida() {
        CuentaBancaria origen = new CuentaBancaria("Origen", 100.0);
        CuentaBancaria destino = new CuentaBancaria("Destino", 0.0);
        origen.transferir(destino, 40.0);
        assertEquals(60.0, origen.getSaldo());
        assertEquals(40.0, destino.getSaldo());
    }

    @Test
    void TC005_ingresarCero() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 100.0);
        assertThrows(IllegalArgumentException.class, () -> cuenta.ingresar(0.0));
    }

    @Test
    void TC006_ingresarCantidadNegativa() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 100.0);
        assertThrows(IllegalArgumentException.class, () -> cuenta.ingresar(-50.0));
    }

    @Test
    void TC007_retirarTodoElSaldo() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 50.0);
        cuenta.retirar(50.0);
        assertEquals(0.0, cuenta.getSaldo());
    }

    @Test
    void TC008_retirarCero() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 100.0);
        assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(0.0));
    }

    @Test
    void TC009_transferirDestinoNulo() {
        CuentaBancaria origen = new CuentaBancaria("Juli", 100.0);
        assertThrows(IllegalArgumentException.class, () -> origen.transferir(null, 50.0));
    }

    @Test
    void TC010_transferirSinFondosSuficientes() {
        CuentaBancaria origen = new CuentaBancaria("Origen", 50.0);
        CuentaBancaria destino = new CuentaBancaria("Destino", 0.0);
        assertThrows(IllegalArgumentException.class, () -> origen.transferir(destino, 100.0));
    }

    @Test
    void TC011_transferirCero() {
        CuentaBancaria origen = new CuentaBancaria("Origen", 100.0);
        CuentaBancaria destino = new CuentaBancaria("Destino", 0.0);
        assertThrows(IllegalArgumentException.class, () -> origen.transferir(destino, 0.0));
    }

    @Test
    void TC012_transferirCantidadNegativa() {
        CuentaBancaria origen = new CuentaBancaria("Origen", 100.0);
        CuentaBancaria destino = new CuentaBancaria("Destino", 0.0);
        assertThrows(IllegalArgumentException.class, () -> origen.transferir(destino, -20.0));
    }

    @Test
    void TC013_registroMovimientoIngreso() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 0.0);
        cuenta.ingresar(50.0);
        assertEquals(2, cuenta.getMovimientos().size());
        assertEquals("Ingreso: +50.0 €", cuenta.getMovimientos().get(1));
    }

    @Test
    void TC014_registroMovimientoRetirada() {
        CuentaBancaria cuenta = new CuentaBancaria("Juli", 100.0);
        cuenta.retirar(30.0);
        assertEquals("Retirada: -30.0 €", cuenta.getMovimientos().get(1));
    }

    @Test
    void TC015_constructorSaldoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new CuentaBancaria("Juli", -100.0));
    }
}

