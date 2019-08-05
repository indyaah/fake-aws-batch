package pro.anuj.tools.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileUtils {

    private static final String PREFIX = "classpath:";

    private static String removePrefix(String s) {
        if (s != null && s.startsWith(FileUtils.PREFIX)) {
            return s.substring(FileUtils.PREFIX.length());
        }
        return s;
    }

    public static File loadFile(String path) throws URISyntaxException {
        if (path.startsWith(PREFIX)) {
            URL defaultImage = FileUtils.class.getResource(removePrefix(path));
            return new File(defaultImage.toURI());
        } else {
            return new File(path);
        }
    }

}
