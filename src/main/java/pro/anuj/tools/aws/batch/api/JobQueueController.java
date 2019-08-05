package pro.anuj.tools.aws.batch.api;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.amazonaws.services.batch.model.CreateJobQueueRequest;
import com.amazonaws.services.batch.model.CreateJobQueueResult;
import com.amazonaws.services.batch.model.DeleteJobQueueRequest;
import com.amazonaws.services.batch.model.DeleteJobQueueResult;
import com.amazonaws.services.batch.model.DescribeJobQueuesRequest;
import com.amazonaws.services.batch.model.DescribeJobQueuesResult;
import com.amazonaws.services.batch.model.UpdateJobQueueRequest;
import com.amazonaws.services.batch.model.UpdateJobQueueResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.anuj.tools.aws.batch.service.JobQueueService;

@RestController
public class JobQueueController implements BatchController {

    private final JobQueueService jobQueueService;

    public JobQueueController(JobQueueService jobQueueService) {
        this.jobQueueService = jobQueueService;
    }

    @PostMapping(value = "/v1/createjobqueue", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateJobQueueResult> create(@RequestBody CreateJobQueueRequest request) {
        return new ResponseEntity<>(jobQueueService.createJobQueue(request), headers(), OK);
    }

    @PostMapping(value = "/v1/updatejobqueue", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateJobQueueResult> update(@RequestBody UpdateJobQueueRequest request) {
        return new ResponseEntity<>(jobQueueService.updateJobQueue(request), headers(), OK);
    }

    @PostMapping(value = "/v1/deletejobqueue", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteJobQueueResult> delete(@RequestBody DeleteJobQueueRequest request) {
        return new ResponseEntity<>(jobQueueService.deleteJobQueue(request), headers(), OK);
    }

    @PostMapping(value = "/v1/describejobqueues", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DescribeJobQueuesResult> describe(@RequestBody DescribeJobQueuesRequest request) {
        return new ResponseEntity<>(jobQueueService.describeJobQueues(request), headers(), OK);
    }
}
