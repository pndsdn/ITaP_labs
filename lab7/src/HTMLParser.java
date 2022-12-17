import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {
    private static final String HTTP_PREFIX = "http://";

    public static String scanForRedirect(String url, BufferedReader in) throws IOException {
        String line = in.readLine();
        if ("HTTP/1.1 301 Moved Permanently".equals(line)) {
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Location:")) {
                    String redirect = line.substring(10);
                    if (!redirect.equals(url)) {
                        return redirect;
                    }
                }
            }
        }
        return null;
    }

    public static List<String> scanHTTPLinks(BufferedReader in) throws IOException {
        String line;
        LinkedList<String> foundLinks = new LinkedList<>();
        while ((line = in.readLine()) != null) {
            Pattern pattern = Pattern.compile("<a href=\"http://[^/]+/\">", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int sIndex = matcher.group().indexOf("http://");
                int eIndex = matcher.group().indexOf(">") - 2;
                foundLinks.add(matcher.group().substring(sIndex, eIndex));
            }
        }
        return foundLinks;
    }
}