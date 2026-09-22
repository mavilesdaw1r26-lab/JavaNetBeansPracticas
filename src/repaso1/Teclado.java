/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repaso1;

import java.util.Scanner;

public class Teclado {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce tu nombre");

        String nom = teclado.nextLine();
        System.out.println("Introduce tu edad");
        int edad = teclado.nextInt();

        System.out.println("Y dime tu nota media");
        double media = teclado.nextDouble();

        System.out.println("Tu nombre es: " + nom + " tu edad es: " + edad + "y tu media es : " + media);

    }
}
