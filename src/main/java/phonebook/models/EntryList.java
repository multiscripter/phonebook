package phonebook.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * Class EntryList realize entity Entry list.
 * Annotation @XmlSeeAlso({Name1.class,Name2.class}) allows to include
 * the specified classes in the JAXBContext context.
 *
 * @author Multiscripter
 * @version 2019-10-01
 * @since 2019-10-01
 */
@XmlRootElement
@XmlSeeAlso({Entry.class})
public class EntryList {
    /**
     * Entry list.
     */
    private List<Entry> list;

    /**
     * Gets entry list.
     * @return entry list.
     */
    @XmlElement(name = "Entry")
    public List<Entry> getEntryList() {
        return this.list;
    }

    /**
     * Sets entry list.
     * @param list entry list.
     */
    public void setEntryList(List<Entry> list) {
        this.list = list;
    }
}
