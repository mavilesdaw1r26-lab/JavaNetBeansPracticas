/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repaso1;

import java.util.Scanner;

public class ClasificadorNotas {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Dime la nota final de DAW");
        double nota = teclado.nextDouble();

        if (nota < 0 || nota > 10) {
            System.out.println("Error: La nota introducida no es valida");
            
        } else if (nota >= 0 && nota <= 4.99) {
            System.out.println("Suspenso");
            
        } else if (nota >= 5.0 && nota <= 6.99) {
            System.out.println("Aprobado");
            
        } else if (nota >= 7.0 && nota <= 8.99) {
            System.out.println("Notable");
            
        } else if (nota >= 9.0 && nota <= 10) {
            System.out.println("Sobresaliente");

        }

    }

}
