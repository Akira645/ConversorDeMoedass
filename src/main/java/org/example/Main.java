package org.example;
import Service.ApiConsumo;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ApiConsumo apiConsumo = new ApiConsumo();
        int escolhaDoUsuario = 0;

        while (escolhaDoUsuario != 7) {
            System.out.println("*****************************************************************");
            System.out.println("Seja bem vindo/a ao Conversor de moedas =] \n");
            System.out.println("1) Real brasileiro =>> Peso argentino \n" +
                    "2) Peso argentino =>> Real Brasileiro\n" +
                    "3) Real brasileiro =>> Dólar\n" +
                    "4) Dólar =>> Real brasileiro\n" +
                    "5) Real brasileiro =>> Euro \n" +
                    "6) Euro =>> Real brasileiro \n" +
                    "7) Sair \n" +
                    "Escolha uma opção válida: ");
            System.out.println("*****************************************************************");

            escolhaDoUsuario = scan.nextInt();

            if (escolhaDoUsuario == 7) {
                System.out.println("Programa encerrado. Obrigado por usar o Conversor de Moedas!");
                break; // Sai do laço imediatamente
            }
            System.out.println("Digite o valor que deseja converter: ");
            double valor = scan.nextDouble();
            apiConsumo.executarRequisicao(escolhaDoUsuario, valor);
        }


    }
}