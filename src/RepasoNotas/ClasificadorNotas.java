package RepasoNotas;

import java.util.Scanner;

public class ClasificadorNotas {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la nota: ");
        double nota = teclado.nextDouble();
        //teclado.nextLine(); limpia el buffer siempre después de leer cualquier cosa que no sea un String para evitar errores.

        try {
            Calificacion resultado = evaluarNota(nota);
            System.out.println(resultado);

        } catch (NotaInvalidaException e) {
            System.out.println(e.getMessage());

        }

    }

    public static Calificacion evaluarNota(double nota) {

        if (nota < 0 || nota > 10) {
            throw new NotaInvalidaException("La nota debe estar entre 0 y 10");
        }
        if (nota < 5.0) {
            return Calificacion.SUSPENSO;
        } else if (nota < 7.0) {
            return Calificacion.APROBADO;
        } else if (nota < 9.0) {
            return Calificacion.NOTABLE;
        } else {
            return Calificacion.SOBRESALIENTE;
        }
    }

}
