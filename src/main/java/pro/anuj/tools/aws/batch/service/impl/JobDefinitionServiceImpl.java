package pro.anuj.tools.aws.batch.service.impl;

import com.amazonaws.services.batch.model.DeregisterJobDefinitionRequest;
import com.amazonaws.services.batch.model.DeregisterJobDefinitionResult;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsRequest;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsResult;
import com.amazonaws.services.batch.model.RegisterJobDefinitionRequest;
import com.amazonaws.services.batch.model.RegisterJobDefinitionResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pro.anuj.tools.exceptions.NotImplementedException;
import pro.anuj.tools.aws.batch.repository.JobDefinitionRepository;
import pro.anuj.tools.aws.batch.service.JobDefinitionService;

@Log4j2
@Component
public class JobDefinitionServiceImpl implements JobDefinitionService {

    private final JobDefinitionRepository jobDefinitionRepository;

    public JobDefinitionServiceImpl(JobDefinitionRepository jobDefinitionRepository) {
        this.jobDefinitionRepository = jobDefinitionRepository;
    }

    @Override
    public DeregisterJobDefinitionResult deregisterJobDefinition(DeregisterJobDefinitionRequest deregisterJobDefinitionRequest) {
        throw new NotImplementedException();
    }

    @Override
    public RegisterJobDefinitionResult registerJobDefinition(RegisterJobDefinitionRequest registerJobDefinitionRequest) {
        throw new NotImplementedException();
    }

    @Override
    public DescribeJobDefinitionsResult describeJobDefinitions(DescribeJobDefinitionsRequest describeJobDefinitionsRequest) {
        throw new NotImplementedException();
    }
}
