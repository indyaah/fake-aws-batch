package pro.anuj.tools.docker.impl;

import com.github.dockerjava.api.DockerClient;

public class DockerServiceImpl {

    private final DockerClient dockerClient;

    public DockerServiceImpl(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }
}
