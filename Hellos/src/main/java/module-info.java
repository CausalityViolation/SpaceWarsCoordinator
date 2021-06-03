module Hellos {

    exports plugins;

    requires ServiceProviderInterface;

    provides Spi.Greeting with plugins.Insult, plugins.HelloThere;

}