/*
 *  Copyright 2017-2019 Adobe.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package pro.anuj.tools.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pro.anuj.tools.exceptions.BatchException;

@Log4j2
@ControllerAdvice
public class FakeBatchExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(final BatchException exception) {
        log.info("Responding with status {}: {}", exception.getStatus(), exception.getMessage());

        final ApiError apiError = new ApiError();
        apiError.setCode(exception.getCode());
        apiError.setMessage(exception.getMessage());

        return ResponseEntity.status(exception.getStatus()).body(apiError);
    }
}
