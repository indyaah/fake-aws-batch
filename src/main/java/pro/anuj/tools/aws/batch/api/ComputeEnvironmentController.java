package pro.anuj.tools.aws.batch.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.amazonaws.services.batch.model.CreateComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.CreateComputeEnvironmentResult;
import com.amazonaws.services.batch.model.DeleteComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.DeleteComputeEnvironmentResult;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsRequest;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsResult;
import com.amazonaws.services.batch.model.UpdateComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.UpdateComputeEnvironmentResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.anuj.tools.aws.batch.service.ComputeEnvironmentService;

@RestController
public class ComputeEnvironmentController {

    private final ComputeEnvironmentService computeEnvironmentService;

    public ComputeEnvironmentController(ComputeEnvironmentService computeEnvironmentService) {
        this.computeEnvironmentService = computeEnvironmentService;
    }

    @PostMapping(value = "/v1/createcomputeenvironment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CreateComputeEnvironmentResult create(@RequestBody CreateComputeEnvironmentRequest request) {
        return computeEnvironmentService.createComputeEnvironment(request);
    }

    @PostMapping(value = "/v1/updatecomputeenvironment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UpdateComputeEnvironmentResult update(@RequestBody UpdateComputeEnvironmentRequest request) {
        return computeEnvironmentService.updateComputeEnvironment(request);
    }

    @PostMapping(value = "/v1/deletecomputeenvironment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DeleteComputeEnvironmentResult delete(@RequestBody DeleteComputeEnvironmentRequest request) {
        return computeEnvironmentService.deleteComputeEnvironment(request);
    }

    @PostMapping(value = "/v1/describecomputeenvironments", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DescribeComputeEnvironmentsResult describe(@RequestBody DescribeComputeEnvironmentsRequest request) {
        return computeEnvironmentService.describeComputeEnvironments(request);
    }
}
