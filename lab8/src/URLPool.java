import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class URLPool {
    private final int maxDepth;
    private int waitingWorkersCount;
    private final int threadsCount;

    private final Set<String> checkedURLs = new HashSet<>();
    private final LinkedList<URLDepthPair> linksToCheck = new LinkedList<>();
    private final LinkedList<URLDepthPair> checkedLinks = new LinkedList<>();

    public URLPool(int maxDepth, int threadsCount) {
        this.maxDepth = maxDepth;
        this.threadsCount = threadsCount;
    }

    public synchronized void addURLPairToCheck(URLDepthPair pair) {
        if (checkedURLs.add(pair.getUrl())) {
            if (pair.depth <= maxDepth) {
                linksToCheck.add(pair);
                notify();
            }
            else {
                checkedLinks.add(pair);
            }
        }
    }

    public synchronized void addURLPairsToCheck(List<URLDepthPair> pairs) {
        for (URLDepthPair pair : pairs) {
            if (checkedURLs.add(pair.getUrl())) {
                if (pair.depth <= maxDepth) {
                    linksToCheck.add(pair);
                }
                else {
                    checkedLinks.add(pair);
                }
            }
        }
        notifyAll();
    }

    public synchronized void addCheckedURLPairs(List<URLDepthPair> pairs) {
        checkedLinks.addAll(pairs);
    }

    public synchronized URLDepthPair getURLPair() {
        ++waitingWorkersCount;
        try {
            while (linksToCheck.isEmpty()) {
                wait();
            }
        }
        catch (InterruptedException e) {}
        --waitingWorkersCount;
        return linksToCheck.removeFirst();
    }

    public List<URLDepthPair> getSites() {
        return checkedLinks;
    }
}
