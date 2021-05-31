import java.io.*;
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

    private void sendImageResponse(OutputStream outputToClient) throws IOException {

        String header;
        byte[] data = new byte[0];

        File file = new File("klutch.png");
        if (!(file.exists() && !file.isDirectory())) {

            header = "HTTP/1.1 404 File Not Found\r\nContent-length: 0\r\n\r\n";

        } else {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {

                data = new byte[(int) file.length()];
                fileInputStream.read(data);

            } catch (IOException error) {
                error.printStackTrace();
            }
        }

        header = "HTTP/1.1 200 OK\r\nContent-Type: image/png\r\nContent-Length: " + data.length + "\r\n\r\n";

        outputToClient.write(header.getBytes());
        outputToClient.write(data);


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
