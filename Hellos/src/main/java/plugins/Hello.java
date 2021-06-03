package plugins;

import Spi.Adress;
import Spi.Greeting;

@Adress("/kenobi")
public class Hello implements Greeting {

    @Override
    public String greeting(String name) {
        return "Hello there GENERAL " + name + "!";
    }
}
