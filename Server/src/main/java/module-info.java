import plugins.HelloThere;

module Server {
    uses HelloThere;
    uses Spi.Greeting;
    requires com.google.gson;
    requires Database;
    requires Engine;
    requires ServiceProviderInterface;
    requires Hellos;
}