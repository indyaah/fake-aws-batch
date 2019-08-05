package pro.anuj.tools.aws.batch.repository;

import com.amazonaws.services.batch.model.JobDefinition;
import java.util.Collection;

public interface JobDefinitionRepository extends Repository<JobDefinition> {

    Collection<JobDefinition> findAllRevisions(String name);

}
