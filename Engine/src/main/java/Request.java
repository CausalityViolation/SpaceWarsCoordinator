import java.util.List;

public class Request {

    HTTPtypes type;
    String url;

    public Request(List<String> tempList) {

        switch (tempList.get(0).substring(0, 2)) {

            case "HE" -> type = HTTPtypes.HEAD;
            case "GE" -> type = HTTPtypes.GET;
            case "PO" -> type = HTTPtypes.POST;
            //Verkar inte kasta exception. Något att titta på senare kanske?
            default -> throw new RuntimeException("Invalid HTTP request type");
        }

        String[] url = tempList.get(1).split(" ");
        this.url = url[1].replace("\r\n", "");
    }

    @Override
    public String toString() {
        return "<Request>" +
                "\nType:  " + type +
                "\nUrl: " + url;
    }
}
