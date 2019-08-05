package pro.anuj.tools.aws.batch.api;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

public interface BatchController {

    default MultiValueMap<String, String> headers() {
        return new HttpHeaders();
    }
}
