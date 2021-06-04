package server;

import Spi.Poem;
import Spi.Url;
import java.util.ServiceLoader;
import static java.util.Objects.nonNull;
public class GreetingsTest {


    public static void main(String[] args) {

        ServiceLoader<Poem> sendDeath = ServiceLoader.load(Poem.class);

        for (Poem helloThere : sendDeath) {
            Url annotation = helloThere.getClass().getAnnotation(Url.class);

            if (nonNull(annotation) && annotation.value().equals("/poem/death")) {
                System.out.println(helloThere.sendPoem());
            }

            else if (nonNull(annotation) && annotation.value().equals("/poem/timber")) {
                System.out.println(helloThere.sendPoem());
            }

        }
    }
}