package pro.anuj.tools.docker;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ContainerRipper {

    private final DockerService dockerService;

    public ContainerRipper(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void run() {

    }

}
