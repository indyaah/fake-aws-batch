package pro.anuj.tools.aws.batch.service;

import com.amazonaws.services.batch.model.CreateComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.CreateComputeEnvironmentResult;
import com.amazonaws.services.batch.model.DeleteComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.DeleteComputeEnvironmentResult;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsRequest;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsResult;
import com.amazonaws.services.batch.model.UpdateComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.UpdateComputeEnvironmentResult;

public interface ComputeEnvironmentService {

    CreateComputeEnvironmentResult createComputeEnvironment(CreateComputeEnvironmentRequest createComputeEnvironmentRequest);

    UpdateComputeEnvironmentResult updateComputeEnvironment(UpdateComputeEnvironmentRequest updateComputeEnvironmentRequest);

    DeleteComputeEnvironmentResult deleteComputeEnvironment(DeleteComputeEnvironmentRequest deleteComputeEnvironmentRequest);

    DescribeComputeEnvironmentsResult describeComputeEnvironments(DescribeComputeEnvironmentsRequest describeComputeEnvironmentsRequest);

}
