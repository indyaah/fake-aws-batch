package pro.anuj.tools.aws.batch.api;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.amazonaws.services.batch.model.CancelJobRequest;
import com.amazonaws.services.batch.model.CancelJobResult;
import com.amazonaws.services.batch.model.DescribeJobsRequest;
import com.amazonaws.services.batch.model.DescribeJobsResult;
import com.amazonaws.services.batch.model.ListJobsRequest;
import com.amazonaws.services.batch.model.ListJobsResult;
import com.amazonaws.services.batch.model.SubmitJobRequest;
import com.amazonaws.services.batch.model.SubmitJobResult;
import com.amazonaws.services.batch.model.TerminateJobRequest;
import com.amazonaws.services.batch.model.TerminateJobResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.anuj.tools.aws.batch.service.JobService;

@RestController
public class JobController implements BatchController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(value = "/v1/canceljob", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CancelJobResult> cancel(@RequestBody CancelJobRequest request) {
        return new ResponseEntity<>(jobService.cancelJob(request), headers(), OK);
    }

    @PostMapping(value = "/v1/submitjob", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SubmitJobResult> submit(@RequestBody SubmitJobRequest request) {
        return new ResponseEntity<>(jobService.submitJob(request), headers(), OK);
    }

    @PostMapping(value = "/v1/terminatejob", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminateJobResult> terminate(@RequestBody TerminateJobRequest request) {
        return new ResponseEntity<>(jobService.terminateJob(request), headers(), OK);
    }

    @PostMapping(value = "/v1/describejobs", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DescribeJobsResult> describe(@RequestBody DescribeJobsRequest request) {
        return new ResponseEntity<>(jobService.describeJobs(request), headers(), OK);
    }

    @PostMapping(value = "/v1/listjobs", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ListJobsResult> list(@RequestBody ListJobsRequest request) {
        return new ResponseEntity<>(jobService.listJobs(request), headers(), OK);
    }

}
