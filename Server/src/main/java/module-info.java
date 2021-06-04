

module Server {

    uses PoemResponse.Death;
    uses Spi.Poem;

    requires com.google.gson;
    requires Database;
    requires Engine;
    requires ServiceProviderInterface;
    requires PoemResponse;


}