package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import com.amazonaws.services.batch.model.JobQueueDetail;
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
import pro.anuj.tools.aws.batch.repository.JobQueueRepository;
import pro.anuj.tools.exceptions.InvalidRequestException;
import pro.anuj.tools.utils.FileUtils;

@Log4j2
@Component
public class InMemoryJobQueueRepository implements JobQueueRepository, InitializingBean {

    private final Map<String, JobQueueDetail> nameToJobQueue = new ConcurrentHashMap<>();
    private final Map<String, JobQueueDetail> arnToJobQueue = new ConcurrentHashMap<>();


    private final ObjectMapper objectMapper;
    private final String defaultConfigPath;

    public InMemoryJobQueueRepository(ObjectMapper objectMapper, @Value("${fake.batch.default-config.job-queue}") String defaultConfigPath) {
        this.objectMapper = objectMapper;
        this.defaultConfigPath = defaultConfigPath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<JobQueueDetail> listOfJobQueues = objectMapper.readValue(FileUtils.loadFile(defaultConfigPath), new TypeReference<List<JobQueueDetail>>() {
        });

        listOfJobQueues.forEach(this::create);
    }

    @Override
    public int count() {
        return arnToJobQueue.size();
    }

    @Override
    public JobQueueDetail find(@NonNull String name) {
        return find(nameToJobQueue.get(name));
    }

    @Override
    public JobQueueDetail findByArn(@NonNull String arn) {
        return find(arnToJobQueue.get(arn));
    }

    private JobQueueDetail find(JobQueueDetail jobQueueDetail) {
        if (jobQueueDetail == null) {
            throw new InvalidRequestException();
        }
        return jobQueueDetail;
    }

    @Override
    public JobQueueDetail delete(@NonNull String name) {
        JobQueueDetail jobQueueDetail = nameToJobQueue.get(name);

        if (jobQueueDetail == null) {
            throw new InvalidRequestException();
        }
        return delete(jobQueueDetail);
    }

    private JobQueueDetail delete(JobQueueDetail jobQueueDetail) {
        nameToJobQueue.remove(jobQueueDetail.getJobQueueName());
        arnToJobQueue.remove(jobQueueDetail.getJobQueueArn());
        return jobQueueDetail;
    }

    @Override
    public JobQueueDetail deleteByArn(@NonNull String arn) {
        JobQueueDetail jobQueueDetail = arnToJobQueue.get(arn);

        if (jobQueueDetail == null) {
            throw new InvalidRequestException();
        }
        return delete(jobQueueDetail);
    }

    @Override
    public String create(@NonNull JobQueueDetail jobQueueDetail) {
        if (jobQueueDetail.getJobQueueArn() == null || jobQueueDetail.getJobQueueName() == null) {
            throw new InvalidRequestException();
        }
        nameToJobQueue.put(jobQueueDetail.getJobQueueName(), jobQueueDetail);
        arnToJobQueue.put(jobQueueDetail.getJobQueueArn(), jobQueueDetail);
        return jobQueueDetail.getJobQueueArn();
    }

    @Override
    public String update(@NonNull JobQueueDetail entity) {
        return create(entity);
    }
}
