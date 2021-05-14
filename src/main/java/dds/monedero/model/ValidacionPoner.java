package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;

public class ValidacionPoner extends Validacion{

  public ValidacionPoner() {
  }

  public void validarDepositosDiarios(Cuenta unaCuenta) {

    if (unaCuenta.getMovimientos().stream().filter(movimiento -> movimiento.isDeposito()).count() >= 3) {
      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
    }
  }

  @Override
  public void validar(double cuanto, Cuenta unaCuenta) {

    super.validarMontoNegativo(cuanto);
    this.validarDepositosDiarios(unaCuenta);
  }
}
