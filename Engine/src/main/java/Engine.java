import java.util.List;

public class Engine {

    public Request handleRequest(List<String> tempList) {
        Request request = new Request(tempList);
        System.out.println(request);
        return request;
    }

    public String postResponse(Request req) {
        return "Hi from POST";
    }

    public String headResponse(Request req) {
        return "Hi from HEAD";
    }

    public String getResponse(Request req) {
        return "Hi from GET";
    }
}
