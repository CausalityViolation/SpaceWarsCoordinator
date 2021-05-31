import java.io.PrintWriter;
import java.util.List;

public class Engine {

    public Request handleRequest(List<String> tempList) {
        Request request = new Request(tempList);
        System.out.println(request);
        return request;
    }

    public String postResponse(Request req) {
        return """
                ========================================
                  ＜￣｀ヽ、　　   　 　／￣＞
                  　ゝ、　　＼ ／⌒ヽ,ノ   /
                  　    ゝ、　`（ ´oωo)／
                         　>　 　 　,ノ
                           ∠_,,,/´””
                       POST, YOU FOOLS!!!
                   Regards From Dolen Support
                ========================================
                """;
    }

    public String headResponse(Request req) {
        return """
                ========================================
                  ＜￣｀ヽ、　　   　 　／￣＞
                  　ゝ、　　＼ ／⌒ヽ,ノ   /
                  　    ゝ、　`（ ´oωo)／
                         　>　 　 　,ノ
                           ∠_,,,/´””
                       HEAD, YOU FOOLS!!!
                   Regards From Dolen Support
                ========================================
                """;
    }

    public String getResponse(Request req) {
        return """
                ========================================
                  ＜￣｀ヽ、　　   　 　／￣＞
                  　ゝ、　　＼ ／⌒ヽ,ノ   /
                  　    ゝ、　`（ ´oωo)／
                         　>　 　 　,ノ
                           ∠_,,,/´””
                       GET, YOU FOOLS!!!
                   Regards From Dolen Support
                ========================================
                """;
    }
}
