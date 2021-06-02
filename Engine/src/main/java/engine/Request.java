package engine;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    private HTTPtypes type;
    private String url;
    private Map<String, String> urlParams = new HashMap<>();

    public HTTPtypes getType() {
        return type;
    }

    public void setType(HTTPtypes type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(Map<String, String> urlParams) {
        this.urlParams = urlParams;
    }

    public Request(List<String> tempList) {

        switch (tempList.get(0).substring(0, 2)) {

            case "HE" -> type = HTTPtypes.HEAD;
            case "GE" -> type = HTTPtypes.GET;
            case "PO" -> type = HTTPtypes.POST;

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
        return "<engine.Request>" +
                "\nType:  " + type +
                "\nUrl: " + url +
                "\nParams: " + urlParams;
    }
}
