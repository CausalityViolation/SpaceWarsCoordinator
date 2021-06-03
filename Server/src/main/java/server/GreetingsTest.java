package server;

import Spi.Adress;
import Spi.Greeting;

import java.util.ServiceLoader;

import static java.util.Objects.nonNull;

public class GreetingsTest {

    public static void main(String[] args) {

        ServiceLoader<Greeting> greetings = ServiceLoader.load(Greeting.class);

        for (Greeting hello : greetings) {

            Adress annotation = hello.getClass().getAnnotation(Adress.class);
            if (nonNull(annotation) && annotation.value().equals("/kenobi")) {

                System.out.println(hello.greeting("Kenobi"));

            }
        }
    }
}
