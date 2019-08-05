package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import static org.junit.Assert.assertEquals;

import com.amazonaws.services.batch.model.JobDetail;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.Before;
import org.junit.Test;
import pro.anuj.tools.exceptions.InvalidRequestException;

public class InMemoryJobRepositoryTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final InMemoryJobRepository sut = new InMemoryJobRepository(
        objectMapper, "classpath:/repository/valid-job.json"
    );

    @Before
    public void setup() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        sut.afterPropertiesSet();
    }


    // NOTE: This test doesnt rely on SUT above as we are trying to test something different.
    @Test(expected = MismatchedInputException.class)
    public void whenInvalidConfigJsonThenBeanCreationFails() throws Exception {
        final InMemoryJobRepository unhappyCase = new InMemoryJobRepository(
            new ObjectMapper(), "classpath:/repository/invalid-job.json"
        );
        unhappyCase.afterPropertiesSet();
    }

    @Test
    public void whenValidConfigThenCorrectDataPopulated() throws Exception {
        assertEquals("Size of initial internal map should be 1", 1, sut.count());
        assertEquals("Look up with name should work.", "test-job", sut.find("test-job").getJobName());
    }


    @Test
    public void whenInsertWithValidDataThenDataPopulated() throws Exception {

        sut.create(new JobDetail().withJobName("test-job-2").withJobId("test-job-arn"));

        assertEquals("Size of initial internal map should be 1", 2, sut.count());
        assertEquals("Look up with name should work.", "test-job", sut.find("test-job").getJobName());
        assertEquals("Look up with name should work.", "test-job-2", sut.find("test-job-2").getJobName());
    }

    @Test(expected = InvalidRequestException.class)
    public void whenInsertWithInvalidDataThenInvalidRequestException() throws Exception {
        sut.create(new JobDetail());
    }
}
