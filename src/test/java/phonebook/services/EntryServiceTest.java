package phonebook.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import phonebook.controllers.AbstractTest;
import phonebook.models.Entry;
import static org.junit.Assert.assertEquals;

/**
 * Class EntryServiceTest tests EntryService.
 *
 * @author Multiscripter
 * @version 2019-09-06
 * @since 2018-09-06
 */
public class EntryServiceTest extends AbstractTest {
    /**
     * Tests public List<Entry> findByLastName(String lastName).
     * @throws Exception exception.
     */
    @Test
    public void testFindByLastName() throws Exception {
        String query = "select * from phone_book where last_name = 'Алексеев'";
        List<Entry> expected = this.getExpected(query);
        List<Entry> actual = this.entryService.findByLastName("Алексеев");
        assertEquals(expected, actual);
    }

    /**
     * Tests public List<Entry> findByNumber(int number);
     * @throws java.lang.Exception exception.
     */
    @Test
    public void testFindByNumber() throws Exception {
        String query = "select * from phone_book where number = 333333";
        List<Entry> expected = this.getExpected(query);
        List<Entry> actual = entryService.findByNumber(333333);
        assertEquals(expected, actual);
    }

    /**
     * Gets list of expected entries.
     * @param query sql select query.
     * @return list of expected entries.
     * @throws java.lang.Exception exception.
     */
    private List<Entry> getExpected(final String query) throws Exception {
        List<HashMap<String, String>> result = driver.select(query);
        List<Entry> expected = new ArrayList<>();
        for (HashMap<String, String> item : result) {
            Entry entry = new Entry();
            entry.setId(Long.parseLong(item.get("id")));
            entry.setNumber(Integer.parseInt(item.get("number")));
            entry.setLastName(item.get("last_name"));
            entry.setFirstName(item.get("first_name"));
            entry.setMiddleName(item.get("middle_name"));
            entry.setAddress(item.get("address"));
            expected.add(entry);
        }
        return expected;
    }
}
