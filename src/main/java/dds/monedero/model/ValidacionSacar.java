package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.SaldoMenorException;

import java.time.LocalDate;

public class ValidacionSacar extends Validacion{

  public ValidacionSacar() {
  }

  public void validarSaldoExcedido(double cuanto, Cuenta unaCuenta) {

    if (unaCuenta.getSaldo() - cuanto < 0) {
      throw new SaldoMenorException("No puede sacar mas de " + unaCuenta.getSaldo() + " $");
    }
  }

  public void validarSaldoSuficiente(double cuanto, double limite) {

    if (cuanto > limite) {
      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + 1000
          + " diarios, límite: " + limite);
    }
  }


@Override
  public void validar(double cuanto, Cuenta unaCuenta) {

    super.validarMontoNegativo(cuanto);
    this.validarSaldoExcedido(cuanto, unaCuenta);
    double montoExtraidoHoy = unaCuenta.getMontoExtraidoA(LocalDate.now());
    double limite = 1000 - montoExtraidoHoy;
    this.validarSaldoSuficiente(cuanto, limite);
  }
}
