import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Server {

    Engine engine = new Engine();

    public void handleConnection(Socket client) {

        BufferedReader inputFromClient;

        try {

            inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));
            Request req = readRequest(inputFromClient);

            var outputToClient = client.getOutputStream();
            sendResponse(outputToClient, req);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(OutputStream outputToClient, Request req) throws IOException {

        if (req.getUrl().endsWith("spaceship")) {
            sendImageResponse(outputToClient);
        }

        switch (req.getType()) {

            case POST -> outputToClient.write(engine.postResponse(req).getBytes());
            case HEAD -> outputToClient.write(engine.headResponse(req).getBytes());
            case GET -> outputToClient.write(engine.getResponse(req).getBytes());
        }

        outputToClient.close();
    }

    private void sendImageResponse(OutputStream outputToClient) throws IOException {

        String header;
        byte[] data = new byte[0];

        File file = Path.of("Server", "target", "classes", "klutch.png").toFile();

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
