import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    Engine engine = new Engine();

    public void handleConnection(Socket client) {

        BufferedReader inputFromClient;

        try {

            inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));
            Request req = readRequest(inputFromClient);
            var outputToClient = new PrintWriter(client.getOutputStream());
            sendResponse(outputToClient, req);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(PrintWriter outputToClient, Request req) {

        switch (req.type) {
            case POST -> outputToClient.println(engine.postResponse(req));
            case HEAD -> outputToClient.println(engine.headResponse(req));
            case GET -> outputToClient.println(engine.getResponse(req));

        }
        outputToClient.close();
    }

    private Request readRequest(BufferedReader inputFromClient) throws IOException {

        List<String> tempList = new ArrayList<>();

        while (true) {

            var line = inputFromClient.readLine();
            if (line == null || line.isEmpty()) {
                break;

            }
            tempList.add(line);
            System.out.println(line);
        }
        return engine.handleRequest(tempList);
    }


}
