package pro.anuj.tools.aws.batch.service;

import com.amazonaws.services.batch.model.CreateJobQueueRequest;
import com.amazonaws.services.batch.model.CreateJobQueueResult;
import com.amazonaws.services.batch.model.DeleteJobQueueRequest;
import com.amazonaws.services.batch.model.DeleteJobQueueResult;
import com.amazonaws.services.batch.model.DescribeJobQueuesRequest;
import com.amazonaws.services.batch.model.DescribeJobQueuesResult;
import com.amazonaws.services.batch.model.UpdateJobQueueRequest;
import com.amazonaws.services.batch.model.UpdateJobQueueResult;

public interface JobQueueService {

    CreateJobQueueResult createJobQueue(CreateJobQueueRequest createJobQueueRequest);

    UpdateJobQueueResult updateJobQueue(UpdateJobQueueRequest updateJobQueueRequest);

    DeleteJobQueueResult deleteJobQueue(DeleteJobQueueRequest deleteJobQueueRequest);

    DescribeJobQueuesResult describeJobQueues(DescribeJobQueuesRequest describeJobQueuesRequest);

}
