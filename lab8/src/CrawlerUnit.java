import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

public class CrawlerUnit {
    private final int timeout;

    public CrawlerUnit(int timeout) {
        this.timeout = timeout;
    }

    public void processPath(final URLDepthPair currentPair,
                             final LinkedList<URLDepthPair> linksToCheck,
                             final LinkedList<URLDepthPair> checkedLinks) throws MalformedURLException {
        SocketClient socketClient = new SocketClient();
        socketClient.request(currentPair.getPath(), currentPair.getHost(), timeout, new SocketClient.SocketCallback() {
            @Override
            public void onSuccess(BufferedReader in) {
                try {
                    for (String url : HTMLParser.scanHTTPLinks(in)) {
                        linksToCheck.add(new URLDepthPair(url, currentPair.depth + 1));
                    }
                }
                catch (IOException e) {
                    currentPair.setScanningException(e);
                }
                finally {
                    checkedLinks.add(currentPair);
                }
            }

            @Override
            public void onError(Exception e) {
                currentPair.setScanningException(e);
                checkedLinks.add(currentPair);
            }
        });
    }
}
