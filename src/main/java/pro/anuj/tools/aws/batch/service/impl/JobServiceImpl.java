package pro.anuj.tools.aws.batch.service.impl;

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
import org.springframework.stereotype.Component;
import pro.anuj.tools.aws.batch.repository.JobRepository;
import pro.anuj.tools.aws.batch.service.JobService;
import pro.anuj.tools.aws.batch.exceptions.NotImplementedException;

@Component
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public CancelJobResult cancelJob(CancelJobRequest cancelJobRequest) {
        throw new NotImplementedException();
    }

    @Override
    public SubmitJobResult submitJob(SubmitJobRequest submitJobRequest) {
        throw new NotImplementedException();
    }

    @Override
    public TerminateJobResult terminateJob(TerminateJobRequest terminateJobRequest) {
        throw new NotImplementedException();
    }

    @Override
    public DescribeJobsResult describeJobs(DescribeJobsRequest describeJobsRequest) {
        throw new NotImplementedException();
    }

    @Override
    public ListJobsResult listJobs(ListJobsRequest listJobsRequest) {
        throw new NotImplementedException();
    }
}
