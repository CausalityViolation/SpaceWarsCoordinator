package server;

import Spi.Poem;
import Spi.Url;
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
import java.util.ServiceLoader;


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


        } else if (req.getUrl().contains("/image")) {

            sendImageResponse(outputToClient, req);


        } else if (req.getUrl().contains("/poem")) {

            sendPoemResponse(outputToClient, req);

        } else {

            switch (req.getType()) {

                case POST -> outputToClient.write(engine.postResponse(req).getBytes());
                case HEAD -> outputToClient.write(engine.headResponse(req).getBytes());
                case GET -> outputToClient.write(engine.getResponse(req).getBytes());

            }
        }
    }

    private void sendImageResponse(OutputStream outputToClient, Request req) throws IOException {

        byte[] data = new byte[0];
        int count = 0;
        File file = Path.of("/app/web/praiseTheSun.jpg").toFile();

        if (req.getUrl().endsWith("/image/klutch")) {

            count++;
            file = Path.of("/app/web/klutch.png").toFile();

        } else if (req.getUrl().endsWith("/image/xwing")) {

            count++;
            file = Path.of("/app/web/xwing.jpeg").toFile();

        } else if (req.getUrl().endsWith("/image/falcon")) {

            count++;
            file = Path.of("/app/web/falcon.jpg").toFile();

        } else if (req.getUrl().endsWith("/image/fighter")) {

            count++;
            file = Path.of("/app/web/fighter.png").toFile();

        } else if (req.getUrl().endsWith("/image/sun")) {

            count++;
            file = Path.of("/app/web/praiseTheSun.jpg").toFile();

        }

        String header = "";

        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            data = new byte[(int) file.length()];
            fileInputStream.read(data);
            var contentType = Files.probeContentType(file.toPath());

            header = "HTTP/1.1 404 Not Found\r\nContent-Type: " + contentType + "\r\nContent-length: " + data.length + "\r\n\r\n";

            if (count > 0) {
                header = "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContent-length: " + data.length + "\r\n\r\n";
            }

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

    private void sendPoemResponse(OutputStream outputToClient, Request req) throws IOException {

        ServiceLoader<Poem> sendDeath = ServiceLoader.load(Poem.class);
        byte[] data;
        int count = 0;
        for (Poem poem : sendDeath) {

            Url annotation = poem.getClass().getAnnotation(Url.class);

            if (req.getUrl().equals(annotation.value())) {

                count++;

                data = poem.sendPoem().getBytes();

                String header = "HTTP/1.1 200 OK\r\nContent-Type: text/txt\r\nContent-length: "
                        + data.length + "\r\n\r\n";

                outputToClient.write(header.getBytes());
                System.out.println(poem.sendPoem());
                outputToClient.write(poem.sendPoem().getBytes());
            }
        }

        if (count == 0) {

            sendImageResponse(outputToClient, req);
        }
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
