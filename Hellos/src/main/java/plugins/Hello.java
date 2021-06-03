package plugins;

import Spi.Greeting;

public class Hello implements Greeting {

    @Override
    public String greeting(String name) {
        return "Hello there " + name + "!";
    }
}
