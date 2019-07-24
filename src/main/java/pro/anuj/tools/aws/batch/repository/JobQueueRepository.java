package pro.anuj.tools.aws.batch.repository;

import com.amazonaws.services.batch.model.CreateJobQueueRequest;
import com.amazonaws.services.batch.model.CreateJobQueueResult;

public interface JobQueueRepository {

    CreateJobQueueResult create(CreateJobQueueRequest createJobQueueRequest);
}
