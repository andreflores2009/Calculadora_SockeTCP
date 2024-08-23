/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2_calculadora_socket_tcp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Andre
 */
/*2) Desenvolva um programa Sockets TCP para um Servidor que o oferece um serviço de calculadora de operações básicas sobre dois números 
reais (soma, subtração, adição e divisão). Desta forma, além de desenvolver a aplicação servidora, desenvolva uma aplicação cliente que apresente ao 
seu usuário um menu com as opções de cálculo, e então receba os dois números pelo teclado e envie uma mensagem para o servidor com os números e também 
indicando a operação a ser feita.*/

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor aguardando conexoes...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                /*Quando um cliente se conecta, serverSocket.accept() é chamado. Essa função bloqueia a execução do programa até que 
                um cliente se conecte, momento em que retorna um novo socket (clientSocket) representando a conexão com esse
                cliente específico.*/
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Configuração para leitura e escrita nos sockets
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

                // Recebe os números e a operação do cliente
                int opcao = dis.readInt();
                double num1 = dis.readDouble();
                double num2 = dis.readDouble();

                // Realiza o cálculo
                String resultado = calcular(num1, num2, opcao);

                // Envia o resultado de volta para o cliente
                dos.writeUTF("Resultado: " + resultado);

                // Fecha a conexão com o cliente
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String calcular(double num1, double num2, int opcao) {
        switch (opcao) {
            case 1:
                return String.valueOf(num1 + num2);
            case 2:
            // Verifica se num1 é menor que num2, e troca a ordem para evitar resultado negativo
                if (num1 < num2) {
                    return String.valueOf(num2 - num1);
                } else {
                    return String.valueOf(num1 - num2);
                }
            case 3:
                return String.valueOf(num1 * num2);
            case 4:
                if (num2 != 0) {
                    return String.valueOf(num1 / num2);
                } else {
                    return "Opcao invalida"; // Indica divisão por zero
                }
            default:
                return "Opcao invalida"; // Indica uma operação inválida
        }
    }
    // Função para realizar o cálculo
    /*private static double calcular(double num1, double num2, int opcao) {
        switch (opcao) {
            case 1:
                return num1 + num2;
            case 2:
                return num1 - num2;
            case 3:
                return num1 * num2;
            case 4:
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN; // Indica divisão por zero
                }
            default:
                return Double.NaN; // Indica uma operação inválida
        }
    }*/
}
