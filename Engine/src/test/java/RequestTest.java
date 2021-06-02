import engine.HTTPtypes;
import engine.Request;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTest {

    List<String> testList = new ArrayList<>();

    @Test
    void requestTypeGetWithRootUrl() {

        String testURL = ("""
                GET / HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);

        testList.add(testURL);
        Request request = new Request(testList);

        assertThat(request.getUrl()).isEqualTo("/");
    }

    @Test
    void requestWithFilePath() {

        String testURL = ("""
                GET /index.html HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);

        testList.add(testURL);
        Request request = new Request(testList);

        assertThat(request.getUrl()).isEqualTo("/index.html");
        assertThat(request.getType()).isEqualTo(HTTPtypes.GET);

    }

    @Test
    void requestTypeHead() {

        String testURL = ("""
                HEAD /index.html HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);

        testList.add(testURL);
        Request request = new Request(testList);

        assertThat(request.getType()).isEqualTo(HTTPtypes.HEAD);

    }

    @Test
    void requestTypeGetWithOneUrlParameter() {

        String testURL = ("""
                GET /products?id=23 HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);

        testList.add(testURL);
        Request request = new Request(testList);

        assertThat(request.getType()).isEqualTo(HTTPtypes.GET);
        assertThat(request.getUrl()).isEqualTo("/products");
        assertThat(request.getUrlParams()).containsEntry("id", "23");
    }

    @Test
    void requestTypePOSTWithTwoUrlParameters() {

        String testURL = ("""
                POST /products?id=23&order=ascend HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);

        testList.add(testURL);
        Request request = new Request(testList);

        assertThat(request.getType()).isEqualTo(HTTPtypes.POST);
        assertThat(request.getUrl()).isEqualTo("/products");
        assertThat(request.getUrlParams()).containsEntry("id", "23").containsEntry("order", "ascend");
    }

    @Test
    void requestTypeGETWithUrlParameterIncludingSpace() {

        String testURL = ("""
                GET /products?text=Hello+there HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);

        testList.add(testURL);
        Request request = new Request(testList);

        assertThat(request.getType()).isEqualTo(HTTPtypes.GET);
        assertThat(request.getUrl()).isEqualTo("/products");
        assertThat(request.getUrlParams()).containsEntry("text", "Hello there");
    }

    @Test
    void requestTypeGETWithUrlParameterIncludingSpaceUsingUrlEncoding() {

        String testURL = ("""
                GET /products?t%20e%20x%20t=M%C3%A5ste%20fixa HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);

        testList.add(testURL);
        Request request = new Request(testList);

        assertThat(request.getType()).isEqualTo(HTTPtypes.GET);
        assertThat(request.getUrl()).isEqualTo("/products");
        assertThat(request.getUrlParams()).containsEntry("t e x t", "Måste fixa");
    }


    @Test
    void requestUrlWithSpaces() {

        String testURL = ("""
                GET /a%20folder/first%20document.pdf HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);

        testList.add(testURL);
        Request request = new Request(testList);

        assertThat(request.getType()).isEqualTo(HTTPtypes.GET);
        assertThat(request.getUrl()).isEqualTo("/a folder/first document.pdf");
        assertThat(request.getUrlParams()).isEmpty();
    }

    @Test
    void invalidRequestThrowsException() {

        testList.add("BAJSKORV");
        testList.add("JIMI HENDRIX");
        assertThrows(RuntimeException.class, () -> new Request(testList));
    }
}
