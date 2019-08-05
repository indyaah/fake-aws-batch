package pro.anuj.tools.aws.batch.api;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.amazonaws.services.batch.model.CreateComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.CreateComputeEnvironmentResult;
import com.amazonaws.services.batch.model.DeleteComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.DeleteComputeEnvironmentResult;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsRequest;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsResult;
import com.amazonaws.services.batch.model.UpdateComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.UpdateComputeEnvironmentResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.anuj.tools.aws.batch.service.ComputeEnvironmentService;

@RestController
public class ComputeEnvironmentController implements BatchController {

    private final ComputeEnvironmentService computeEnvironmentService;

    public ComputeEnvironmentController(ComputeEnvironmentService computeEnvironmentService) {
        this.computeEnvironmentService = computeEnvironmentService;
    }

    @PostMapping(value = "/v1/createcomputeenvironment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateComputeEnvironmentResult> create(@RequestBody CreateComputeEnvironmentRequest request) {
        return new ResponseEntity<>(computeEnvironmentService.createComputeEnvironment(request), headers(), OK);
    }

    @PostMapping(value = "/v1/updatecomputeenvironment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateComputeEnvironmentResult> update(@RequestBody UpdateComputeEnvironmentRequest request) {
        return new ResponseEntity<>(computeEnvironmentService.updateComputeEnvironment(request), headers(), OK);
    }

    @PostMapping(value = "/v1/deletecomputeenvironment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteComputeEnvironmentResult> delete(@RequestBody DeleteComputeEnvironmentRequest request) {
        return new ResponseEntity<>(computeEnvironmentService.deleteComputeEnvironment(request), headers(), OK);
    }

    @PostMapping(value = "/v1/describecomputeenvironments", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DescribeComputeEnvironmentsResult> describe(@RequestBody DescribeComputeEnvironmentsRequest request) {
        return new ResponseEntity<>(computeEnvironmentService.describeComputeEnvironments(request), headers(), OK);
    }
}
