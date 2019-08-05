package pro.anuj.tools.aws.batch.api;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.amazonaws.services.batch.model.DeregisterJobDefinitionRequest;
import com.amazonaws.services.batch.model.DeregisterJobDefinitionResult;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsRequest;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsResult;
import com.amazonaws.services.batch.model.RegisterJobDefinitionRequest;
import com.amazonaws.services.batch.model.RegisterJobDefinitionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.anuj.tools.aws.batch.service.JobDefinitionService;

@RestController
public class JobDefinitionController implements BatchController {

    private final JobDefinitionService jobDefinitionService;

    public JobDefinitionController(JobDefinitionService jobDefinitionService) {
        this.jobDefinitionService = jobDefinitionService;
    }

    @PostMapping(value = "/v1/deregisterjobdefinition", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeregisterJobDefinitionResult> deregister(@RequestBody DeregisterJobDefinitionRequest request) {
        return new ResponseEntity<>(jobDefinitionService.deregisterJobDefinition(request), headers(), OK);
    }

    @PostMapping(value = "/v1/registerjobdefinition", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterJobDefinitionResult> register(@RequestBody RegisterJobDefinitionRequest request) {
        return new ResponseEntity<>(jobDefinitionService.registerJobDefinition(request), headers(), OK);
    }

    @PostMapping(value = "/v1/describejobdefinitions", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DescribeJobDefinitionsResult> describe(@RequestBody DescribeJobDefinitionsRequest request) {
        return new ResponseEntity<>(jobDefinitionService.describeJobDefinitions(request), headers(), OK);
    }
}
