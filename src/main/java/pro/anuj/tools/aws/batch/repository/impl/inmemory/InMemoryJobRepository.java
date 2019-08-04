package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import com.amazonaws.services.batch.model.JobDefinition;
import com.amazonaws.services.batch.model.SubmitJobRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import pro.anuj.tools.aws.batch.exceptions.NotImplementedException;
import pro.anuj.tools.aws.batch.repository.JobRepository;

@Component
public class InMemoryJobRepository implements JobRepository {

    private final Map<String, Map<Integer, JobDefinition>> jobDefinitionMap = new HashMap<>();

    @Override
    public String create(SubmitJobRequest submitJobRequest) {
        throw new NotImplementedException();
    }

    @Override
    public SubmitJobRequest find(String id) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<SubmitJobRequest> findAll(Collection<String> ids) {
        throw new NotImplementedException();
    }
}
