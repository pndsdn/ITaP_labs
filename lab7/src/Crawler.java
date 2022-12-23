import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Crawler {
    private final LinkedList<URLDepthPair> linksToCheck = new LinkedList<>();
    private final LinkedList<URLDepthPair> checkedLinks = new LinkedList<>();

    public static void main(String[] args) {

        Crawler crawler = new Crawler();
        System.out.println("found: " + crawler.scanWebPage("http://info.cern.ch/", 1));
    }

    private String getSites() {
        return checkedLinks.toString();
    }

    private String scanWebPage(String startUrl, int maxDepth) {
        linksToCheck.add(new URLDepthPair(startUrl, 0));
        try {
            process(maxDepth);
        }
        catch (NumberFormatException | IOException e) {
            System.out.println("usage: java Crawler " + startUrl + " " + maxDepth + "\n exception: " + e);
        }
        return getSites();
    }

    private void process(int maxDepth) throws IOException {
        while (!linksToCheck.isEmpty()) {
            URLDepthPair currentPair = linksToCheck.removeFirst();
            if (currentPair.depth <= maxDepth) {
                processPath(currentPair);
            }
        }
    }

    private void processPath(final URLDepthPair currentPair) throws IOException {
        if (checkedLinks.contains(currentPair)) {
            return;
        }

        SocketClient socketClient = new SocketClient();
        socketClient.request(currentPair.getPath(), currentPair.getHost(), new SocketClient.SocketCallback() {
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
