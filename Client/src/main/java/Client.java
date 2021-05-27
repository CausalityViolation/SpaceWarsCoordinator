import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//Lokal Dator:
//localhost 127.0.0.1

//Blombergs Dator:
//178.174.162.51

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 80);

            var output = new PrintWriter(socket.getOutputStream());

                /*output.println("==========================================");
                output.println("       ＜￣｀ヽ、　　   　 　／￣＞");
                output.println("       　ゝ、　　＼ ／⌒ヽ,ノ   /");
                output.println("       　    ゝ、　`（ ´oωo)／");
                output.println("              　>　 　 　,ノ");
                output.println("                ∠_,,,/´””");
                output.println("            CRASH, YOU FOOLS!!!");
                output.println("     DDOS ATTACK From Dolen Support");
                output.println("==========================================\r\n\r\n");

                 */

            output.println("HEAD / HTTP/1.1");
            output.println("Host: www.example.com\r\n\r\n");
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
