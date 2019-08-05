package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import com.amazonaws.services.batch.model.JobDefinition;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pro.anuj.tools.aws.batch.repository.JobDefinitionRepository;
import pro.anuj.tools.exceptions.InvalidRequestException;
import pro.anuj.tools.utils.FileUtils;

@Log4j2
@Component
public class InMemoryJobDefinitionRepository implements JobDefinitionRepository, InitializingBean {

    private final Map<String, ConcurrentHashMap<Integer, JobDefinition>> nameToJobDefinition = new ConcurrentHashMap<>();
    private final Map<String, JobDefinition> arnToJobDefinition = new ConcurrentHashMap<>();

    private final ObjectMapper objectMapper;
    private final String defaultConfigPath;

    public InMemoryJobDefinitionRepository(ObjectMapper objectMapper, @Value("${fake.batch.default-config.job-definition}") String defaultConfigPath) {
        this.objectMapper = objectMapper;
        this.defaultConfigPath = defaultConfigPath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<JobDefinition> listOfJobDefs = objectMapper.readValue(FileUtils.loadFile(defaultConfigPath), new TypeReference<List<JobDefinition>>() {
        });

        listOfJobDefs.forEach(this::create);

    }

    @Override
    public int count() {
        return arnToJobDefinition.size();
    }

    @Override
    public JobDefinition find(@NonNull String name) {
        ConcurrentHashMap<Integer, JobDefinition> revisionMap = nameToJobDefinition.get(name);
        if (revisionMap != null && revisionMap.size() > 0) {
            return revisionMap.get(1);
        }
        throw new InvalidRequestException();
    }

    @Override
    public JobDefinition findByArn(@NonNull String jobDefinitionArn) {
        return arnToJobDefinition.get(jobDefinitionArn);
    }

    @Override
    public JobDefinition delete(@NonNull String name) {
        throw new InvalidRequestException();
    }

    @Override
    public JobDefinition deleteByArn(@NonNull String arn) {
        JobDefinition jobDefinition = arnToJobDefinition.get(arn);
        if (jobDefinition == null) {
            throw new InvalidRequestException();
        }

        arnToJobDefinition.remove(jobDefinition.getJobDefinitionArn());
        nameToJobDefinition.get(jobDefinition.getJobDefinitionName()).remove(jobDefinition.getRevision());
        return jobDefinition;
    }

    @Override
    public String create(@NonNull JobDefinition jobDefinition) {
        if (jobDefinition.getJobDefinitionArn() == null || jobDefinition.getJobDefinitionArn() == null || jobDefinition.getRevision() == null) {
            throw new InvalidRequestException();
        }
        ConcurrentHashMap<Integer, JobDefinition> existingRevisionMap = nameToJobDefinition.get(jobDefinition.getJobDefinitionName());
        ConcurrentHashMap<Integer, JobDefinition> revisionMap = existingRevisionMap != null ? existingRevisionMap : new ConcurrentHashMap<>();
        revisionMap.put(jobDefinition.getRevision(), jobDefinition);
        nameToJobDefinition.put(jobDefinition.getJobDefinitionName(), revisionMap);
        arnToJobDefinition.put(jobDefinition.getJobDefinitionArn(), jobDefinition);
        return jobDefinition.getJobDefinitionArn();
    }

    @Override
    public String update(@NonNull JobDefinition entity) {
        return create(entity);
    }

    @Override
    public Collection<JobDefinition> findAllRevisions(@NonNull String name) {
        ConcurrentHashMap<Integer, JobDefinition> revisionMap = nameToJobDefinition.get(name);
        if (revisionMap == null || revisionMap.size() == 0) {
            throw new InvalidRequestException();
        }
        return revisionMap.values();
    }

}
