package server;

import com.google.gson.Gson;
import database.DatabaseManagement;
import engine.Engine;
import engine.Request;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Server {

    Engine engine = new Engine();
    DatabaseManagement db = new DatabaseManagement();

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

        if (req.getUrl().endsWith("store")) {

            sendJsonResponse(outputToClient);


        } else if (req.getUrl().contains("add")) {

            db.addShip(req);
            outputToClient.write(engine.getResponse(req).getBytes());


        } else if (req.getUrl().length() > 1) {

            sendImageResponse(outputToClient, req);


        } else {

            switch (req.getType()) {

                case POST -> outputToClient.write(engine.postResponse(req).getBytes());
                case HEAD -> outputToClient.write(engine.headResponse(req).getBytes());
                case GET -> outputToClient.write(engine.getResponse(req).getBytes());

            }
        }
    }

    private void sendImageResponse(OutputStream outputToClient, Request req) throws IOException {

        String header = "";
        byte[] data = new byte[0];
        File file = Path.of("server", "target", "classes", "praiseTheSun.jpg").toFile();

        if (req.getUrl().endsWith("klutch")) {

            file = Path.of("server", "target", "classes", "klutch.png").toFile();

        } else if (req.getUrl().endsWith("xwing")) {

            file = Path.of("server", "target", "classes", "xwing.jpeg").toFile();

        } else if (req.getUrl().endsWith("falcon")) {

            file = Path.of("server", "target", "classes", "falcon.jpg").toFile();

        } else if (req.getUrl().endsWith("fighter")) {

            file = Path.of("server", "target", "classes", "fighter.png").toFile();

        } else if (req.getUrl().endsWith("sun")) {

            file = Path.of("server", "target", "classes", "praiseTheSun.jpg").toFile();

        }


        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            data = new byte[(int) file.length()];
            fileInputStream.read(data);

            var contentType = Files.probeContentType(file.toPath());

            header = "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContent-length: " + data.length + "\r\n\r\n";

        } catch (IOException error) {
            error.printStackTrace();
        }

        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();

    }

    private void sendJsonResponse(OutputStream outputToClient) throws IOException {

        DatabaseManagement dbm = new DatabaseManagement();

        Gson gson = new Gson();

        String json = gson.toJson(dbm.showShips());
        System.out.println(json);

        byte[] data = json.getBytes(StandardCharsets.UTF_8);

        String header = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-length: " + data.length + "\r\n\r\n";

        outputToClient.write(header.getBytes());
        outputToClient.write(data);
        outputToClient.flush();

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
