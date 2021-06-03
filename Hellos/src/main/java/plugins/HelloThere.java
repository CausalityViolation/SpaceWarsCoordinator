package plugins;

import Spi.Url;
import Spi.Greeting;

@Url("/kenobi")
public class HelloThere implements Greeting {

    @Override
    public String greeting(String name) {
        return "Hello There GENERAL " + name + "!";
    }
}
