import java.io.IOException;
import java.util.LinkedList;

public class CrawlerTask implements Runnable {
    private final URLPool urlPool;
    private final CrawlerUnit crawler;

    public CrawlerTask(URLPool urlPool, int timeout) {
        this.urlPool = urlPool;
        this.crawler = new CrawlerUnit(timeout);
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            URLDepthPair currentPair = urlPool.getURLPair();
            LinkedList<URLDepthPair> checkedLinks = new LinkedList<>();
            LinkedList<URLDepthPair> linksToCheck = new LinkedList<>();

            try {
                crawler.processPath(currentPair, linksToCheck, checkedLinks);
            }
            catch (IOException e) {
                currentPair.setScanningException(e);
            }
            finally {
                urlPool.addURLPairsToCheck(linksToCheck);
                urlPool.addCheckedURLPairs(checkedLinks);
            }
        }
    }
}
