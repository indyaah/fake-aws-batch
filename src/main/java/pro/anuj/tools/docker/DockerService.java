package pro.anuj.tools.docker;

import com.amazonaws.services.batch.model.ContainerProperties;

public interface DockerService {

    String createContainer(ContainerProperties containerProperties);

    void startContainer(String containerId);

    void listContainers();

    void checkContainerStatus();
}
