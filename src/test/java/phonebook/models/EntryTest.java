package phonebook.models;

import java.util.Objects;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 * Class "EntryTest" tests class "Entry".
 *
 * @author Multiscripter
 * @version 2019-09-19
 * @since 2018-09-19
 */
public class EntryTest {
    /**
     * Phone book entry.
     */
    private Entry entry;

    /**
     * Actions before each test.
     */
    @Before
    public void beforeTest() {
        this.entry = new Entry(0L, 999999, "Иванов", "Иван", "Иванович", "Мира ул. д.99");
    }

    /**
     * Tests public boolean equals(Object o).
     * Objects are equal.
     */
    @Test
    public void testEqualsObjectsAreEqual() {
        Entry expected = new Entry();
        expected.setId(0L);
        expected.setNumber(999999);
        expected.setLastName("Иванов");
        expected.setFirstName("Иван");
        expected.setMiddleName("Иванович");
        expected.setAddress("Мира ул. д.99");
        assertEquals(expected, this.entry);
    }

    /**
     * Tests public boolean equals(Object obj).
     * 2 references to one object.
     */
    @Test
    public void testEquals2refsOfOneObject() {
        Entry obj = this.entry;
        assertEquals(obj, this.entry);
    }

    /**
     * Tests public boolean equals(Object obj).
     * Comparison with null.
     */
    @Test
    public void testEqualsWithNull() {
        Entry obj = null;
        assertFalse(this.entry.equals(obj));
    }

    /**
     * Tests public boolean equals(Object obj).
     * Comparing objects of different classes.
     */
    @Test
    public void testEqualsWithDifferentClasses() {
        assertFalse(this.entry.equals(""));
    }

    /**
     * Tests public int hashCode().
     */
    @Test
    public void testHashCode() {
        int expected = Objects.hash(0L, 999999, "Иванов", "Иван", "Иванович", "Мира ул. д.99");
        int actual = this.entry.hashCode();
        assertEquals(expected, actual);
    }

    /**
     * Tests public String toString().
     */
    @Test
    public void testToString() {
        String expected = "Entry{id: 0, number: 999999, lastName: Иванов, firstName: Иван, middleName: Иванович, address: Мира ул. д.99}";
        assertEquals(expected, this.entry.toString());
    }
}
