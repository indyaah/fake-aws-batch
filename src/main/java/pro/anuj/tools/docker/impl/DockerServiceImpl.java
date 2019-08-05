package pro.anuj.tools.docker.impl;

import com.amazonaws.services.batch.model.ContainerProperties;
import com.github.dockerjava.api.DockerClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pro.anuj.tools.exceptions.NotImplementedException;
import pro.anuj.tools.docker.DockerService;

@Log4j2
@Component
public class DockerServiceImpl implements DockerService {

    private final DockerClient dockerClient;

    public DockerServiceImpl(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @Override
    public String createContainer(ContainerProperties containerProperties) {
        throw new NotImplementedException();
    }

    @Override
    public void startContainer(String containerId) {
        throw new NotImplementedException();
    }

    @Override
    public void listContainers() {
        throw new NotImplementedException();
    }

    @Override
    public void checkContainerStatus() {
        throw new NotImplementedException();
    }
}
