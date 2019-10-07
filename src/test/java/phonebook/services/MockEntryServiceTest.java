package phonebook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import phonebook.config.HyperSQLconfig;
import phonebook.dao.IEntryRepository;
import phonebook.models.Entry;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Class MockEntryServiceTest tests EntryService using Mockito.
 *
 * @author Multiscripter
 * @version 2019-09-07
 * @since 2018-09-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"phonebook.services"})
@EnableJpaRepositories("phonebook.dao")
public class MockEntryServiceTest {

    @MockBean
    private IEntryRepository entryRepository;

    @Autowired
    private EntryService entryService;

    /**
     * Tests public List<Entry> findByLastName(String lastName).
     * @throws java.lang.Exception exception.
     */
    @Test
    public void testFindByLastName() throws Exception {
        Entry expected = new Entry(
                999L, 9999999, "LName", "FName", "MName", ""
        );
        when(entryRepository.findById(0L)).thenReturn(Optional.of(new Entry(
                999L, 9999999, "LName", "FName", "MName", ""
        )));
        Entry actual = this.entryService.findById(0L);
        assertEquals(expected, actual);
    }

    /**
     * Tests public List<Entry> findByNumber(int number);
     * @throws java.lang.Exception exception.
     */
    @Test
    public void testFindByNumber() throws Exception {
        List<Entry> expected = new ArrayList<>();
        expected.add(new Entry());
        List<Entry> list = new ArrayList<>();
        list.add(new Entry());
        when(entryRepository.findByNumber(0)).thenReturn(list);
        List<Entry> actual = this.entryService.findByNumber(0);
        // Two references to the same object.
        assertEquals(expected, actual);
    }
}