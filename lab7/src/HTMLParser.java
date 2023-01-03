import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {
    private static final String HTTP_PREFIX = "http://";
    private static final String PATTERN = "[(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}" +
            "\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

    public static List<String> scanHTTPLinks(BufferedReader in) throws IOException {
        String line;
        line = in.readLine(); // null
        LinkedList<String> foundLinks = new LinkedList<>();
        while (line != null) {
            Pattern pattern = Pattern.compile("<a href=\"" + HTTP_PREFIX +
                    PATTERN + "\"", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int sIndex = matcher.group().indexOf(HTTP_PREFIX);
                int eIndex = matcher.group().substring(sIndex).indexOf("\"") + sIndex;
                foundLinks.add(matcher.group().substring(sIndex, eIndex));
            }
            line = in.readLine();
        }
        return foundLinks;
    }
}