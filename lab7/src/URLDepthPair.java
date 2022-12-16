public class URLDepthPair {
    String url;
    int depth;

    URLDepthPair(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return url + " " + depth;
    }
}
