module PoemResponse {

    exports PoemResponse;

    requires ServiceProviderInterface;

    provides Spi.Poem with PoemResponse.Death, PoemResponse.Timber;
}