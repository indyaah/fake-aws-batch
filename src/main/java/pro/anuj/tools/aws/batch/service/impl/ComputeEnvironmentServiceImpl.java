package pro.anuj.tools.aws.batch.service.impl;

import com.amazonaws.services.batch.model.CreateComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.CreateComputeEnvironmentResult;
import com.amazonaws.services.batch.model.DeleteComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.DeleteComputeEnvironmentResult;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsRequest;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsResult;
import com.amazonaws.services.batch.model.UpdateComputeEnvironmentRequest;
import com.amazonaws.services.batch.model.UpdateComputeEnvironmentResult;
import pro.anuj.tools.aws.batch.repository.ComputeEnvironmentRepository;
import pro.anuj.tools.aws.batch.service.ComputeEnvironmentService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ComputeEnvironmentServiceImpl implements ComputeEnvironmentService {

    private final ComputeEnvironmentRepository computeEnvironmentRepository;

    public ComputeEnvironmentServiceImpl(ComputeEnvironmentRepository computeEnvironmentRepository) {
        this.computeEnvironmentRepository = computeEnvironmentRepository;
    }

    @Override
    public CreateComputeEnvironmentResult createComputeEnvironment(CreateComputeEnvironmentRequest createComputeEnvironmentRequest) {
        throw new NotImplementedException();
    }

    @Override
    public UpdateComputeEnvironmentResult updateComputeEnvironment(UpdateComputeEnvironmentRequest updateComputeEnvironmentRequest) {
        throw new NotImplementedException();
    }

    @Override
    public DeleteComputeEnvironmentResult deleteComputeEnvironment(DeleteComputeEnvironmentRequest deleteComputeEnvironmentRequest) {
        throw new NotImplementedException();
    }

    @Override
    public DescribeComputeEnvironmentsResult describeComputeEnvironments(DescribeComputeEnvironmentsRequest describeComputeEnvironmentsRequest) {
        throw new NotImplementedException();
    }
}
