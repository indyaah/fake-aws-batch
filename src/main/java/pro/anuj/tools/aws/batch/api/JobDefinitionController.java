package pro.anuj.tools.aws.batch.api;

import com.amazonaws.services.batch.model.DeregisterJobDefinitionRequest;
import com.amazonaws.services.batch.model.DeregisterJobDefinitionResult;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsRequest;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsResult;
import com.amazonaws.services.batch.model.RegisterJobDefinitionRequest;
import com.amazonaws.services.batch.model.RegisterJobDefinitionResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.anuj.tools.aws.batch.service.JobDefinitionService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class JobDefinitionController {
    private final JobDefinitionService jobDefinitionService;

    public JobDefinitionController(JobDefinitionService jobDefinitionService) {
        this.jobDefinitionService = jobDefinitionService;
    }

    @PostMapping(value = "/v1/deregisterjobdefinition", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DeregisterJobDefinitionResult deregister(@RequestBody DeregisterJobDefinitionRequest request) {
        return jobDefinitionService.deregisterJobDefinition(request);
    }

    @PostMapping(value = "/v1/registerjobdefinition", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public RegisterJobDefinitionResult register(@RequestBody RegisterJobDefinitionRequest request) {
        return jobDefinitionService.registerJobDefinition(request);
    }

    @PostMapping(value = "/v1/describejobdefinitions", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DescribeJobDefinitionsResult describe(@RequestBody DescribeJobDefinitionsRequest request) {
        return jobDefinitionService.describeJobDefinitions(request);
    }
}
