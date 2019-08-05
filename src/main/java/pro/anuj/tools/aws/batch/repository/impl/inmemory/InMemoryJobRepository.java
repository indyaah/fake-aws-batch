package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import com.amazonaws.services.batch.model.JobDetail;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pro.anuj.tools.exceptions.InvalidRequestException;
import pro.anuj.tools.aws.batch.repository.JobRepository;
import pro.anuj.tools.utils.FileUtils;

@Log4j2
@Component
public class InMemoryJobRepository implements JobRepository, InitializingBean {

    private final Map<String, JobDetail> nameToJobDef = new ConcurrentHashMap<>();
    private final Map<String, JobDetail> arnToJobDef = new ConcurrentHashMap<>();

    private final ObjectMapper objectMapper;
    private final String defaultConfigPath;

    public InMemoryJobRepository(ObjectMapper objectMapper, @Value("${fake.batch.default-config.job}") String defaultConfigPath) {
        this.objectMapper = objectMapper;
        this.defaultConfigPath = defaultConfigPath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<JobDetail> listOfJobDefs = objectMapper.readValue(FileUtils.loadFile(defaultConfigPath), new TypeReference<List<JobDetail>>() {
        });
        listOfJobDefs.forEach(this::create);
    }

    @Override
    public int count() {
        return arnToJobDef.size();
    }

    @Override
    public JobDetail find(@NonNull String name) {
        return find(nameToJobDef.get(name));
    }

    @Override
    public JobDetail findByArn(@NonNull String arn) {
        return find(arnToJobDef.get(arn));
    }

    private JobDetail find(JobDetail jobDetail) {
        if (jobDetail != null) {
            return jobDetail;
        }
        throw new InvalidRequestException();
    }

    @Override
    public JobDetail delete(@NonNull String name) {
        return delete(find(name));
    }

    @Override
    public JobDetail deleteByArn(@NonNull String arn) {
        return delete(findByArn(arn));
    }

    @Override
    public String create(@NonNull JobDetail jobDetail) {
        if (jobDetail.getJobId() == null || jobDetail.getJobName() == null)
            throw new InvalidRequestException();
        nameToJobDef.put(jobDetail.getJobName(), jobDetail);
        arnToJobDef.put(jobDetail.getJobId(), jobDetail);
        return jobDetail.getJobId();
    }

    @Override
    public String update(@NonNull JobDetail entity) {
        return create(entity);
    }

    private JobDetail delete(JobDetail jobDetail) {
        if (jobDetail == null) {
            throw new InvalidRequestException();
        }
        nameToJobDef.remove(jobDetail.getJobName());
        arnToJobDef.remove(jobDetail.getJobId());
        return jobDetail;
    }

}
