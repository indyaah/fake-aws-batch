package pro.anuj.tools.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import com.github.dockerjava.netty.NettyDockerCmdExecFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class DockerConfig {

    private final Integer dockerClientReadTimeOut;
    private final Integer dockerClientConnectTimeout;
    private final Integer dockerClientMaxTotalConnections;
    private final Integer dockerClientMaxPerRouteConnection;

    Environment environment;

    public DockerConfig(
        @Value("docker.client.read_timeout") final Integer dockerClientReadTimeOut,
        @Value("docker.client.connection_timeout") final Integer dockerClientConnectTimeout,
        @Value("docker.client.max_total_connections") final Integer dockerClientMaxTotalConnections,
        @Value("docker.client.max_per_route_connections") final Integer dockerClientMaxPerRouteConnection) {

        this.dockerClientReadTimeOut = dockerClientReadTimeOut;
        this.dockerClientConnectTimeout = dockerClientConnectTimeout;
        this.dockerClientMaxTotalConnections = dockerClientMaxTotalConnections;
        this.dockerClientMaxPerRouteConnection = dockerClientMaxPerRouteConnection;
    }

    @Bean
//    @ConditionalOnExpression("${docker.client}.equals('netty')")
    @ConditionalOnProperty(prefix = "docker.client", name = "type", havingValue = "netty")
    public DockerCmdExecFactory nettyDockerCmdExecFactory() {
        return new NettyDockerCmdExecFactory()
            .withReadTimeout(dockerClientReadTimeOut)
            .withConnectTimeout(dockerClientConnectTimeout);
    }

    @Bean
//    @ConditionalOnExpression("${docker.client}.equals('jersey')")
    @ConditionalOnProperty(prefix = "docker.client", name = "type", havingValue = "jersey")
    public DockerCmdExecFactory jettyDockerCmdExecFactory() {
        return new JerseyDockerCmdExecFactory()
            .withReadTimeout(dockerClientReadTimeOut)
            .withConnectTimeout(dockerClientConnectTimeout)
            .withMaxTotalConnections(dockerClientMaxTotalConnections)
            .withMaxPerRouteConnections(dockerClientMaxPerRouteConnection);
    }

    @Bean
    public DockerClientConfig dockerClientConfig() {
        return DefaultDockerClientConfig
            .createDefaultConfigBuilder()
            .build();
    }

    @Bean
    public DockerClient dockerClient(DockerClientConfig dockerClientConfig, DockerCmdExecFactory dockerCmdExecFactory) {
        return DockerClientBuilder.getInstance(dockerClientConfig)
            .withDockerCmdExecFactory(dockerCmdExecFactory)
            .build();
    }

}
