import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Crawler {
    private final LinkedList<URLDepthPair> linksToCheck = new LinkedList<>();
    private final LinkedList<URLDepthPair> checkedLinks = new LinkedList<>();

    public static void main(String[] args) {

        Crawler crawler = new Crawler();
        System.out.println("found: " + crawler.scanWebPage(args[0], Integer.parseInt(args[1])));
    }

    private LinkedList<URLDepthPair> getSites() {
        return checkedLinks;
    }

    private LinkedList<URLDepthPair> scanWebPage(String startUrl, int maxDepth) {
        try {
            process(startUrl, maxDepth);
        }
        catch (NumberFormatException | IOException e) {
            System.out.println("usage: java Crawler " + startUrl + " " + maxDepth + "\n exception: " + e);
        }
        return getSites();
    }

    private void process(String url, int maxDepth) throws IOException {
        linksToCheck.add(new URLDepthPair(url, 0));
        while (!linksToCheck.isEmpty()) {
            URLDepthPair currentPair = linksToCheck.removeFirst();
            if (currentPair.depth < maxDepth) {
                processPath(currentPair);
            }
        }
    }

    private void processPath(final URLDepthPair currentPair) throws IOException {
        if (isKnown(currentPair)) {
            return;
        }

        SocketClient socketClient = new SocketClient();
        socketClient.request(currentPair.getPath(), currentPair.getHost(), new SocketClient.SocketCallback() {
            @Override
            public void onSuccess(BufferedReader in) {
                try {
                    String redirect = HTMLParser.scanForRedirect(currentPair.getUrl(), in);
                    if (redirect != null) {
                        currentPair.setUrl(redirect);
                        linksToCheck.add(currentPair);
                    }
                    else {
                        for (String url : HTMLParser.scanHTTPLinks(in)) {
                            linksToCheck.add(new URLDepthPair(url, currentPair.depth + 1));
                        }
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

    private boolean isKnown(URLDepthPair pair) {
            return linksToCheck.contains(pair) || checkedLinks.contains(pair);
    }
}
