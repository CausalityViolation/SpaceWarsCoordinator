package plugins;

import Spi.Url;
import Spi.Greeting;

@Url("/insult")
public class Insult implements Greeting {

    @Override
    public String greeting(String name) {
        return "You're not very nice, " + name + "!";
    }
}
