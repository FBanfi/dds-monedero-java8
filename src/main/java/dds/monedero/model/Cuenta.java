package dds.monedero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

  private double saldo = 0;
  private List<Movimiento> movimientos = new ArrayList<>();
  private Validacion validadorPoner = new ValidacionPoner();
  private Validacion validadorSacar = new ValidacionSacar();

  public Cuenta() {
    saldo = 0;
  }

  public Cuenta(double montoInicial) {
    saldo = montoInicial;
  }

  public void setMovimientos(List<Movimiento> movimientos) {
    this.movimientos = movimientos;
  }

  public void poner(double cuanto) {

    validadorPoner.validar(cuanto, this);
    movimientos.add(new Movimiento(LocalDate.now(), cuanto, true));
  }

  public void sacar(double cuanto) {

    validadorSacar.validar(cuanto, this);
    movimientos.add(new Movimiento(LocalDate.now(), cuanto, false));
  }

  public void agregarMovimiento(Movimiento movimiento) {

    movimientos.add(movimiento);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return getMovimientos().stream()
        .filter(movimiento -> !movimiento.isDeposito() && movimiento.getFecha().equals(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public List<Movimiento> getMovimientos() {
    return movimientos;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

}
