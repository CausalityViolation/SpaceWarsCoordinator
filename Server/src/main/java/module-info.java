module Server {
    uses plugins.Hello;
    uses Spi.Greeting;
    requires com.google.gson;
    requires Database;
    requires Engine;
    requires ServiceProviderInterface;
    requires Hellos;
}