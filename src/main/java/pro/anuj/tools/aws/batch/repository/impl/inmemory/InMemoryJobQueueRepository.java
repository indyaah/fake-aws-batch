package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import com.amazonaws.services.batch.model.CreateJobQueueRequest;
import com.amazonaws.services.batch.model.CreateJobQueueResult;
import pro.anuj.tools.aws.batch.exceptions.NotImplementedException;
import pro.anuj.tools.aws.batch.repository.JobQueueRepository;

public class InMemoryJobQueueRepository implements JobQueueRepository {

    @Override
    public CreateJobQueueResult create(CreateJobQueueRequest createJobQueueRequest) {
        throw new NotImplementedException();
    }
}
