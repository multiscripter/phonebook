package phonebook.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request
        .MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.assertEquals;
import phonebook.models.Entry;
import phonebook.models.EntryList;

/**
 * Class RouterTest tests Router.
 *
 * @author Multiscripter
 * @version 2019-09-06
 * @since 2018-09-05
 */
public class RouterTest extends AbstractTest {
    /**
     * Tests public @ResponseBody Entry getEntry(@PathVariable long id).
     * @throws Exception exception.
     */
    @Test
    public void testGetEntryReturnsJsonWithEntry() throws Exception {
        String uri = "/get-entry/1/";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(uri);
        builder = builder.accept(MediaType.APPLICATION_JSON_VALUE);
        ResultActions resultActions = this.mvc.perform(builder);
        MvcResult mvcResult = resultActions.andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Entry actual = super.mapFromJson(content, Entry.class);
        Entry expected = new Entry();
        expected.setId(1L);
        expected.setNumber(111111);
        expected.setLastName("Алексеев");
        expected.setFirstName("Алексей");
        expected.setMiddleName("Алексеевич");
        expected.setAddress("Фокина ул., д. 1");
        assertEquals(expected, actual);
    }

    /**
     * Tests public @ResponseBody EntryList getEntries().
     * @throws Exception exception.
     */
    @Test
    public void testGetEntriesReturnsJsonWithEntries() throws Exception {
        String uri = "/get-entries/";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(uri);
        builder = builder.accept(MediaType.APPLICATION_JSON_VALUE);
        ResultActions resultActions = this.mvc.perform(builder);
        MvcResult mvcResult = resultActions.andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        EntryList mapped = super.mapFromJson(content, EntryList.class);
        List<Entry> actual = mapped.getEntryList();
        List<Entry> expected = new ArrayList<>();
        String query = "select * from phone_book";
        List<HashMap<String, String>> result = driver.select(query);
        this.fillEntryList(expected, result);
        assertEquals(expected, actual);
    }

    /**
     * Fills Entry list.
     * @param list empty list;
     * @param data entry dta.
     * @throws Exception any exception.
     */
    private void fillEntryList(List<Entry> list, List<HashMap<String, String>> data) throws Exception {
        for (HashMap<String, String> item : data) {
            Entry entry = new Entry();
            entry.setId(Long.parseLong(item.get("id")));
            entry.setNumber(Integer.parseInt(item.get("number")));
            entry.setLastName(item.get("last_name"));
            entry.setFirstName(item.get("first_name"));
            entry.setMiddleName(item.get("middle_name"));
            entry.setAddress(item.get("address"));
            list.add(entry);
        }
    }
}
