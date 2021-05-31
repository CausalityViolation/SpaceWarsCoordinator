
import java.util.List;

public class Engine {

    public Request handleRequest(List<String> tempList) {

        Request request = new Request(tempList);
        System.out.println(request);
        return request;
    }

    public String postResponse(Request req) {
        return """
                HTTP/1.1 200 OK
                Content-Length: 0\r\n\r\n
                """;
    }

    public String headResponse(Request req) {
        return """
                HTTP/1.1 404
                Content-Length: 0\r\n\r\n
                """;
    }

    public String getResponse(Request req) {
        return """
                HTTP/1.1 200 OK\
                Content-Length: 0\r\n\r\n
                                
                ====================================
                  ＜￣｀ヽ、　　   　 　／￣＞
                  　ゝ、　　＼ ／⌒ヽ,ノ   /
                  　    ゝ、　`（ ´oωo)／
                         　>　 　 　,ノ
                           ∠_,,,/´””
                        GET, YOU FOOLS!!!
                   Regards From Dolen Support
                ====================================
                                
                """;
    }

}
