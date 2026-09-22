/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repaso1;

import java.util.Scanner;

public class SioNo {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce la nota final de programacion");
        double nota = teclado.nextDouble();

        if (nota >= 5.0) {
            System.out.println("!Aprobado Has superado el modulo.");
        } else {
            System.out.println("Suspenso. Toca apretar para la recuperacion");

        }

    }

}
