package pro.anuj.tools.aws.batch.service.impl;

import com.amazonaws.services.batch.model.CreateJobQueueRequest;
import com.amazonaws.services.batch.model.CreateJobQueueResult;
import com.amazonaws.services.batch.model.DeleteJobQueueRequest;
import com.amazonaws.services.batch.model.DeleteJobQueueResult;
import com.amazonaws.services.batch.model.DescribeJobQueuesRequest;
import com.amazonaws.services.batch.model.DescribeJobQueuesResult;
import com.amazonaws.services.batch.model.UpdateJobQueueRequest;
import com.amazonaws.services.batch.model.UpdateJobQueueResult;
import pro.anuj.tools.aws.batch.repository.JobQueueRepository;
import pro.anuj.tools.aws.batch.service.JobQueueService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JobQueueServiceImpl implements JobQueueService {

    private final JobQueueRepository jobQueueRepository;

    public JobQueueServiceImpl(JobQueueRepository jobQueueRepository) {
        this.jobQueueRepository = jobQueueRepository;
    }

    @Override
    public CreateJobQueueResult createJobQueue(CreateJobQueueRequest createJobQueueRequest) {
        throw new NotImplementedException();
    }

    @Override
    public UpdateJobQueueResult updateJobQueue(UpdateJobQueueRequest updateJobQueueRequest) {
        throw new NotImplementedException();
    }

    @Override
    public DeleteJobQueueResult deleteJobQueue(DeleteJobQueueRequest deleteJobQueueRequest) {
        throw new NotImplementedException();
    }

    @Override
    public DescribeJobQueuesResult describeJobQueues(DescribeJobQueuesRequest describeJobQueuesRequest) {
        throw new NotImplementedException();
    }
}
