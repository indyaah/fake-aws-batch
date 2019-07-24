package pro.anuj.tools.aws.batch.service;

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

public interface JobService {

    CancelJobResult cancelJob(CancelJobRequest cancelJobRequest);

    SubmitJobResult submitJob(SubmitJobRequest submitJobRequest);

    TerminateJobResult terminateJob(TerminateJobRequest terminateJobRequest);

    DescribeJobsResult describeJobs(DescribeJobsRequest describeJobsRequest);

    ListJobsResult listJobs(ListJobsRequest listJobsRequest);
}
