package pro.anuj.tools.aws.batch.repository.impl.inmemory;

import static org.junit.Assert.assertEquals;

import com.amazonaws.services.batch.model.JobQueueDetail;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Before;
import org.junit.Test;
import pro.anuj.tools.exceptions.InvalidRequestException;

public class InMemoryJobQueueRepositoryTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final InMemoryJobQueueRepository sut = new InMemoryJobQueueRepository(
        objectMapper, "classpath:/repository/valid-job-queue.json"
    );

    @Before
    public void setup() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        sut.afterPropertiesSet();
    }


    // NOTE: This test doesnt rely on SUT above as we are trying to test something different.
    @Test(expected = UnrecognizedPropertyException.class)
    public void whenInvalidConfigJsonThenBeanCreationFails() throws Exception {
        final InMemoryJobQueueRepository unhappyCase = new InMemoryJobQueueRepository(
            new ObjectMapper(), "classpath:/repository/invalid-job-queue.json"
        );
        unhappyCase.afterPropertiesSet();
    }

    @Test
    public void whenValidConfigThenCorrectDataPopulated() throws Exception {
        assertEquals("Size of initial internal map should be 1", 1, sut.count());
        assertEquals("Look up with name should work.", "test-job-queue", sut.find("test-job-queue").getJobQueueName());
    }


    @Test
    public void whenInsertWithValidDataThenDataPopulated() throws Exception {

        sut.create(new JobQueueDetail().withJobQueueName("test-job-queue-2").withJobQueueArn("test-job-queue-arn"));

        assertEquals("Size of initial internal map should be 1", 2, sut.count());
        assertEquals("Look up with name should work.", "test-job-queue", sut.find("test-job-queue").getJobQueueName());
        assertEquals("Look up with name should work.", "test-job-queue-2", sut.find("test-job-queue-2").getJobQueueName());
    }

    @Test(expected = InvalidRequestException.class)
    public void whenInsertWithInvalidDataThenInvalidRequestException() throws Exception {
        sut.create(new JobQueueDetail());
    }
}
