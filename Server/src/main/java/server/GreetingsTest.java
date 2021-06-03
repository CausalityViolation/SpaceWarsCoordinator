package server;

import Spi.Url;
import Spi.Greeting;

import java.util.ServiceLoader;

import static java.util.Objects.nonNull;

public class GreetingsTest {


    public static void main(String[] args) {

        ServiceLoader<Greeting> greetings = ServiceLoader.load(Greeting.class);

        for (Greeting greeting : greetings) {
            Url annotation = greeting.getClass().getAnnotation(Url.class);

            if (nonNull(annotation) && annotation.value().equals("/kenobi")) {
                System.out.println(greeting.greeting("Kenobi"));
            }
        }
    }
}