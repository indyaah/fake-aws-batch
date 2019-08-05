package pro.anuj.tools.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BatchException extends RuntimeException {

    private final int status;
    private final String code;
    private final String message;

}
