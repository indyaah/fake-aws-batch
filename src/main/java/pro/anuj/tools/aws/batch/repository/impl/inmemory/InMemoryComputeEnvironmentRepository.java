package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import com.amazonaws.services.batch.model.ComputeEnvironmentDetail;
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
import org.springframework.util.StringUtils;
import pro.anuj.tools.exceptions.InvalidRequestException;
import pro.anuj.tools.aws.batch.repository.ComputeEnvironmentRepository;
import pro.anuj.tools.utils.FileUtils;

@Log4j2
@Component
public class InMemoryComputeEnvironmentRepository implements ComputeEnvironmentRepository, InitializingBean {

    private final Map<String, ComputeEnvironmentDetail> nameToComputeEnv = new ConcurrentHashMap<>();
    private final Map<String, ComputeEnvironmentDetail> arnToComputeEnv = new ConcurrentHashMap<>();

    private final ObjectMapper objectMapper;
    private final String defaultConfigPath;

    public InMemoryComputeEnvironmentRepository(ObjectMapper objectMapper, @Value("${fake.batch.default-config.compute-environment}") String defaultConfigPath) {
        this.objectMapper = objectMapper;
        this.defaultConfigPath = defaultConfigPath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<ComputeEnvironmentDetail> listOfComputeEnv = objectMapper.readValue(FileUtils.loadFile(defaultConfigPath), new TypeReference<List<ComputeEnvironmentDetail>>() {
        });

        listOfComputeEnv.forEach(this::create);
    }


    public int count() {
        return arnToComputeEnv.size();
    }

    private ComputeEnvironmentDetail find(ComputeEnvironmentDetail computeEnvironmentDetail) {
        if (computeEnvironmentDetail != null) {
            return computeEnvironmentDetail;
        }
        throw new InvalidRequestException();
    }

    @Override
    public ComputeEnvironmentDetail find(@NonNull String name) {
        return find(nameToComputeEnv.get(name));
    }

    @Override
    public ComputeEnvironmentDetail findByArn(@NonNull String arn) {
        return find(arnToComputeEnv.get(arn));
    }

    @Override
    public ComputeEnvironmentDetail delete(@NonNull String name) {
        return delete(nameToComputeEnv.get(name));
    }

    @Override
    public ComputeEnvironmentDetail deleteByArn(@NonNull String arn) {
        return delete(arnToComputeEnv.get(arn));
    }

    private ComputeEnvironmentDetail delete(ComputeEnvironmentDetail computeEnvironmentDetail) {
        if (computeEnvironmentDetail == null) {
            throw new InvalidRequestException();
        }

        nameToComputeEnv.remove(computeEnvironmentDetail.getComputeEnvironmentName());
        arnToComputeEnv.remove(computeEnvironmentDetail.getComputeEnvironmentArn());
        return computeEnvironmentDetail;
    }

    @Override
    public String create(@NonNull ComputeEnvironmentDetail computeEnvironmentDetail) {
        if (StringUtils.isEmpty(computeEnvironmentDetail.getComputeEnvironmentName())) {
            throw new InvalidRequestException();
        }
        nameToComputeEnv.put(computeEnvironmentDetail.getComputeEnvironmentName(), computeEnvironmentDetail);
        arnToComputeEnv.put(computeEnvironmentDetail.getComputeEnvironmentArn(), computeEnvironmentDetail);
        return computeEnvironmentDetail.getComputeEnvironmentArn();
    }

    @Override
    public String update(@NonNull ComputeEnvironmentDetail entity) {
        return create(entity);
    }
}
