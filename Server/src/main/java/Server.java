import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public void handleConnection(Socket client) {
        BufferedReader inputFromClient = null;
        try {
            inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));
            readRequest(inputFromClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readRequest(BufferedReader inputFromClient) throws IOException {
        List<String> tempList = new ArrayList<>();

        while (true) {
            var line = inputFromClient.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            tempList.add(line);
            System.out.println(line);
        }
    }
}
