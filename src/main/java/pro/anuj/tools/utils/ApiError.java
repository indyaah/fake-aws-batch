package pro.anuj.tools.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("Error")
public class ApiError {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Resource")
    private String resource;

    @JsonProperty("RequestId")
    private String requestId;
}
