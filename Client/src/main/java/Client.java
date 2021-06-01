import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//Lokal Dator:
//localhost 127.0.0.1

//Blombergs Dator:
//178.174.162.51:5050

//Siönäs Dator:
//92.33.174.12:80

//Egen dator TCP
//212.181.145.114:80

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 80);

            var output = new PrintWriter(socket.getOutputStream());

            output.println("GET hello there");
            output.println("Host: www.TeamMartin.com\r\n\r\n");
            output.flush();

            var inputFromServer = new BufferedReader(new InputStreamReader((socket.getInputStream())));

            while (true) {

                var line = inputFromServer.readLine();

                if (line == null || line.isEmpty()) {
                    break;
                }
                System.out.println(line);
            }

            output.close();
            inputFromServer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
