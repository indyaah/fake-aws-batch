package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import static org.junit.Assert.assertEquals;

import com.amazonaws.services.batch.model.JobDefinition;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Before;
import org.junit.Test;
import pro.anuj.tools.exceptions.InvalidRequestException;

public class InMemoryJobDefinitionRepositoryTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final InMemoryJobDefinitionRepository sut = new InMemoryJobDefinitionRepository(
        objectMapper, "classpath:/repository/valid-job-definition.json"
    );

    @Before
    public void setup() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        sut.afterPropertiesSet();
    }


    // NOTE: This test doesnt rely on SUT above as we are trying to test something different.
    @Test(expected = UnrecognizedPropertyException.class)
    public void whenInvalidConfigJsonThenBeanCreationFails() throws Exception {
        final InMemoryComputeEnvironmentRepository unhappyCase = new InMemoryComputeEnvironmentRepository(
            new ObjectMapper(), "classpath:/repository/invalid-job-definition.json"
        );
        unhappyCase.afterPropertiesSet();
    }

    @Test
    public void whenValidConfigThenCorrectDataPopulated() throws Exception {
        assertEquals("Size of initial internal map should be 1", 1, sut.count());
        assertEquals("Look up with name should work.", "test-job", sut.find("test-job").getJobDefinitionName());
    }


    @Test
    public void whenInsertWithValidDataThenDataPopulated() throws Exception {

        sut.create(new JobDefinition().withJobDefinitionName("test-job-2").withJobDefinitionArn("idk:1").withRevision(1));

        assertEquals("Size of initial internal map should be 1", 2, sut.count());
        assertEquals("Look up with name should work.", "test-job", sut.find("test-job").getJobDefinitionName());
        assertEquals("Look up with name should work.", "test-job-2", sut.find("test-job-2").getJobDefinitionName());
    }

    @Test(expected = InvalidRequestException.class)
    public void whenInsertWithInvalidDataThenInvalidRequestException() throws Exception {
        sut.create(new JobDefinition());
    }
}
