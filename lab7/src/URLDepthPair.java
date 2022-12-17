import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {
    private String url;
    public int depth;
    private Exception scanningException;

    URLDepthPair(String url, int depth) {
        this.url = url;
        this.depth = depth;
        scanningException = null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() throws MalformedURLException {
        return new URL(url).getHost();
    }

    public String getPath() throws MalformedURLException {
        return new URL(url).getPath();
    }

    public Exception getScanningException() {
        return scanningException;
    }

    public void setScanningException(Exception e) {
        scanningException = e;
    }

    @Override
    public String toString() {
        return url + " " + depth;
    }
}
