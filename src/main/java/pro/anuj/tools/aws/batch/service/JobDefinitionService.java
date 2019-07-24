package pro.anuj.tools.aws.batch.service;

import com.amazonaws.services.batch.model.DeregisterJobDefinitionRequest;
import com.amazonaws.services.batch.model.DeregisterJobDefinitionResult;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsRequest;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsResult;
import com.amazonaws.services.batch.model.RegisterJobDefinitionRequest;
import com.amazonaws.services.batch.model.RegisterJobDefinitionResult;

public interface JobDefinitionService {


    DeregisterJobDefinitionResult deregisterJobDefinition(DeregisterJobDefinitionRequest deregisterJobDefinitionRequest);

    RegisterJobDefinitionResult registerJobDefinition(RegisterJobDefinitionRequest registerJobDefinitionRequest);

    DescribeJobDefinitionsResult describeJobDefinitions(DescribeJobDefinitionsRequest describeJobDefinitionsRequest);

}
