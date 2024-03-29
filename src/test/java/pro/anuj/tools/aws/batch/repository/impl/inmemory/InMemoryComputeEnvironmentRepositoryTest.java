package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import static org.junit.Assert.assertEquals;

import com.amazonaws.services.batch.model.ComputeEnvironmentDetail;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Before;
import org.junit.Test;
import pro.anuj.tools.exceptions.InvalidRequestException;

public class InMemoryComputeEnvironmentRepositoryTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final InMemoryComputeEnvironmentRepository sut = new InMemoryComputeEnvironmentRepository(
        objectMapper, "classpath:/repository/valid-compute-environment.json"
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
            new ObjectMapper(), "classpath:/repository/invalid-compute-environment.json"
        );
        unhappyCase.afterPropertiesSet();
    }

    @Test
    public void whenValidConfigThenCorrectDataPopulated() throws Exception {
        assertEquals("Size of initial internal map should be 1", 1, sut.count());
        assertEquals("Look up with name should work.", "test-compute-env", sut.find("test-compute-env").getComputeEnvironmentName());
    }


    @Test
    public void whenInsertWithValidDataThenDataPopulated() throws Exception {

        sut.create(new ComputeEnvironmentDetail().withComputeEnvironmentName("test-compute-env-2").withComputeEnvironmentArn("test-compute-env-2"));

        assertEquals("Size of initial internal map should be 1", 2, sut.count());
        assertEquals("Look up with name should work.", "test-compute-env", sut.find("test-compute-env").getComputeEnvironmentName());
        assertEquals("Look up with name should work.", "test-compute-env-2", sut.find("test-compute-env-2").getComputeEnvironmentName());
    }

    @Test(expected = InvalidRequestException.class)
    public void whenInsertWithInvalidDataThenInvalidRequestException() throws Exception {
        sut.create(new ComputeEnvironmentDetail());
    }
}
