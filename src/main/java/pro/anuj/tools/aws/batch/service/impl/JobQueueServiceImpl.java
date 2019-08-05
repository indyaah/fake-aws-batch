package pro.anuj.tools.aws.batch.service.impl;

import com.amazonaws.services.batch.model.CreateJobQueueRequest;
import com.amazonaws.services.batch.model.CreateJobQueueResult;
import com.amazonaws.services.batch.model.DeleteJobQueueRequest;
import com.amazonaws.services.batch.model.DeleteJobQueueResult;
import com.amazonaws.services.batch.model.DescribeJobQueuesRequest;
import com.amazonaws.services.batch.model.DescribeJobQueuesResult;
import com.amazonaws.services.batch.model.UpdateJobQueueRequest;
import com.amazonaws.services.batch.model.UpdateJobQueueResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pro.anuj.tools.aws.batch.repository.JobQueueRepository;
import pro.anuj.tools.aws.batch.service.JobQueueService;
import pro.anuj.tools.exceptions.NotImplementedException;

@Log4j2
@Component
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
