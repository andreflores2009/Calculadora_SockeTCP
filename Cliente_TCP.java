/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex2_calculadora_socket_tcp;

/**
 *
 * @author Andre
 */
/*2) Desenvolva um programa Sockets TCP para um Servidor que o oferece um serviço de calculadora de operações básicas sobre dois números
reais (soma, subtração, adição e divisão). Desta forma, além de desenvolver a aplicação servidora, desenvolva uma aplicação cliente que apresente 
ao seu usuário um menu com as opções de cálculo, e então receba os dois números pelo teclado e envie uma mensagem para o servidor com os números e 
também indicando a operação a ser feita.*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Cliente_TCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                try {
            // Conecta ao servidor
            Socket socket = new Socket("localhost", 12345);

            // Configuração para leitura e escrita nos sockets
            Scanner scanner = new Scanner(System.in);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            // Menu de opções
            System.out.println("Escolha uma operacao:");
            System.out.println("1. Soma");
            System.out.println("2. Subtracao");
            System.out.println("3. Multiplicacao");
            System.out.println("4. Divisao");
            System.out.println("OPCAO: ");
            // Recebe a escolha do usuario
            int opcao = Integer.parseInt(scanner.nextLine());

            // Recebe os números do usuario
            System.out.print("Digite o primeiro numero: ");
            double num1 = Double.parseDouble(scanner.nextLine());

            System.out.print("Digite o segundo numero: ");
            double num2 = Double.parseDouble(scanner.nextLine());

            // Envia os numeros e a operaçao para o servidor
            dos.writeInt(opcao);
            dos.writeDouble(num1);
            dos.writeDouble(num2);

            // Recebe e exibe o resultado do servidor
            String resultado = dis.readUTF();                     
            System.out.println("Resultado do servidor: " + resultado);
            

            // Fecha a conexao com o servidor
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
