package pro.anuj.tools.aws.batch.api;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.anuj.tools.aws.batch.service.JobService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("/v1")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(value = "/canceljob", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CancelJobResult cancel(@RequestBody CancelJobRequest request) {
        return jobService.cancelJob(request);
    }

    @PostMapping(value = "/submitjob", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SubmitJobResult submit(@RequestBody SubmitJobRequest request) {
        return jobService.submitJob(request);
    }

    @PostMapping(value = "/terminatejob", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public TerminateJobResult terminate(@RequestBody TerminateJobRequest request) {
        return jobService.terminateJob(request);
    }

    @PostMapping(value = "/describejobs", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DescribeJobsResult describe(@RequestBody DescribeJobsRequest request) {
        return jobService.describeJobs(request);
    }

    @PostMapping(value = "/listjobs", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ListJobsResult list(@RequestBody ListJobsRequest request) {
        return jobService.listJobs(request);
    }
}