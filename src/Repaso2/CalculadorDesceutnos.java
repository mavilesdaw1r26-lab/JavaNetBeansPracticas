package Repaso2;

import java.util.Scanner;

public class CalculadorDesceutnos {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce el precio: ");
        double precioBase = teclado.nextDouble();

        System.out.println("Introduce tipo de cliente (BRONCE, PLATA, ORO): ");
        String textoIngresado = teclado.next().toUpperCase();

        TipoCliente cliente = TipoCliente.valueOf(textoIngresado);

        try {
            double precioFinal = calcularPrecioFinal(precioBase, cliente);
            System.out.println("Precio final con descuento: " + precioFinal);

        } catch (PrecioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double calcularPrecioFinal(double precioBase, TipoCliente cliente) throws PrecioInvalidoException {

        if (precioBase <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor que 0");
        }
        if (cliente == TipoCliente.BRONCE) {
            return precioBase * 0.95;
        } else if (cliente == TipoCliente.PLATA) {
            return precioBase * 0.85;
        } else {
            return precioBase * 0.70;
        }
    }
}
