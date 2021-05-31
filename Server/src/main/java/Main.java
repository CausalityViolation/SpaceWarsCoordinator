import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        Server server = new Server();
        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(80)) {

            System.out.println("Awaiting Connection From Client..");

            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(() -> server.handleConnection(client));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
