module PoemResponse {
    requires ServiceProviderInterface;

    exports PoemResponse;

    provides Spi.Poem with PoemResponse.Death, PoemResponse.Timber;
}