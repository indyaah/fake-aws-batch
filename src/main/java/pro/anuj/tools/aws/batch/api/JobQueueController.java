package pro.anuj.tools.aws.batch.api;

import com.amazonaws.services.batch.model.CreateJobQueueRequest;
import com.amazonaws.services.batch.model.CreateJobQueueResult;
import com.amazonaws.services.batch.model.DeleteJobQueueRequest;
import com.amazonaws.services.batch.model.DeleteJobQueueResult;
import com.amazonaws.services.batch.model.DescribeJobQueuesRequest;
import com.amazonaws.services.batch.model.DescribeJobQueuesResult;
import com.amazonaws.services.batch.model.UpdateJobQueueRequest;
import com.amazonaws.services.batch.model.UpdateJobQueueResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.anuj.tools.aws.batch.service.JobQueueService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class JobQueueController {

    private final JobQueueService jobQueueService;

    public JobQueueController(JobQueueService jobQueueService) {
        this.jobQueueService = jobQueueService;
    }

    @PostMapping(value = "/v1/createjobqueue", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CreateJobQueueResult create(@RequestBody CreateJobQueueRequest request) {
        return jobQueueService.createJobQueue(request);
    }

    @PostMapping(value = "/v1/updatejobqueue", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UpdateJobQueueResult update(@RequestBody UpdateJobQueueRequest request) {
        return jobQueueService.updateJobQueue(request);
    }

    @PostMapping(value = "/v1/deletejobqueue", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DeleteJobQueueResult delete(@RequestBody DeleteJobQueueRequest request) {
        return jobQueueService.deleteJobQueue(request);
    }

    @PostMapping(value = "/v1/describejobqueues", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DescribeJobQueuesResult describe(@RequestBody DescribeJobQueuesRequest request) {
        return jobQueueService.describeJobQueues(request);
    }
}
