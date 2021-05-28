import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    HTTPtypes type;
    String url;
    Map<String, String> urlParams = new HashMap<>();

    public Request(List<String> tempList) {

        switch (tempList.get(0).substring(0, 2)) {

            case "HE" -> type = HTTPtypes.HEAD;
            case "GE" -> type = HTTPtypes.GET;
            case "PO" -> type = HTTPtypes.POST;
            //Verkar inte kasta exception. Något att titta på senare kanske?
            default -> throw new RuntimeException("Invalid HTTP request type");
        }

        String[] url = tempList.get(0).split("[ ]");

        this.url = setUrl(url[1]);
    }

    private String setUrl(String s) {

        s = URLDecoder.decode(s);

        if (s.contains("?")) {

            String[] splitUrl = s.split("[?=&]");

            if (splitUrl.length > 1) {
                for (int i = 1; i < (splitUrl.length - 1); i = i + 2) {
                    urlParams.put(splitUrl[i], splitUrl[i + 1]);
                }
                return splitUrl[0];
            }
        }
        return s;
    }

    @Override
    public String toString() {
        return "<Request>" +
                "\nType:  " + type +
                "\nUrl: " + url +
                "\nParams: " + urlParams;
    }
}
