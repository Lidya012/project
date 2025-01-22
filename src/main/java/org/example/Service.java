package org.example;

import org.example.Util.SAVED.LoadState;
import org.example.Command.Avtorization;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {
    private static final int PORT = 8000;
    private static ExecutorService threadPool;

    public static void main(String[] args) {
        LoadState.loadState();

        threadPool = Executors.newFixedThreadPool(10); // Пул из 10 потоков
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                threadPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {

            String request = (String) input.readObject();
            System.out.println("Получен запрос: " + request);
            String response = String.valueOf(processRequest(request));
            output.writeObject(response);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
                private boolean processRequest (String request){
                switch (request.toLowerCase()) {

                    case "login":
                        return Avtorization.avtorization();
                    default:
                        System.out.println("Неизвестная команда: " + request);
                        return false;
                }
            }
        }

