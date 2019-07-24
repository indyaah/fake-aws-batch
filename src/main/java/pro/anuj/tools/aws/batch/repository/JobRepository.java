package pro.anuj.tools.aws.batch.repository;

import com.amazonaws.services.batch.model.SubmitJobRequest;

import java.util.Collection;

public interface JobRepository {

    String create(SubmitJobRequest submitJobRequest);

    SubmitJobRequest find(String id);

    Collection<SubmitJobRequest> findAll(Collection<String> ids);

}

