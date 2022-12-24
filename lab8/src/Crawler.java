import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Crawler {
    public static void main(String[] args) {
        final String url;
        final int maxDepth;
        final int threadsCount;
        final int timeout;

        try {
            url = args[0];
            maxDepth = Integer.parseInt(args[1]);
            threadsCount = Integer.parseInt(args[2]);
            if (args.length == 4) {
                timeout = Integer.parseInt(args[3]);
            }
            else {
                timeout = 5000;
            }
        }
        catch (Exception e) {
            System.out.printf("Unexpected arguments. Expected '[string], [int], [int]', got %s", Arrays.toString(args));
            System.out.println();
            return;
        }

        Crawler crawler = new Crawler();
        System.out.println(crawler.scanWebPage(url, maxDepth, threadsCount, timeout));
    }

    private List<URLDepthPair> scanWebPage(String url, int maxDepth, int threadsCount, int timeout) {
        final URLPool urlPool = new URLPool(maxDepth, threadsCount);
        ExecutorService crawlers = Executors.newFixedThreadPool(threadsCount);

        final ArrayList<CrawlerTask> tasks = new ArrayList<>();
        urlPool.addURLPairToCheck(new URLDepthPair(url, 0));

        for (int i = 0; i < threadsCount; ++i) {
            tasks.add(new CrawlerTask(urlPool, timeout));
            crawlers.submit(tasks.get(tasks.size() - 1));
        }

        while (urlPool.isInProgress()) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        crawlers.shutdownNow();
        return urlPool.getSites();
    }
}
