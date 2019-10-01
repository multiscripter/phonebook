package phonebook.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * Класс EntryList реализует модель данных Список записей.
 * Аннотация @XmlSeeAlso({Name1.class,Name2.class}) позволяет
 * включить в контекст JAXBContext указанные классы.
 *
 * @version 2019-10-01
 * @since 2019-10-01
 */
@XmlRootElement
@XmlSeeAlso({Entry.class})
public class EntryList {
    /**
     * Список записей.
     */
    private List<Entry> list;

    /**
     * Получает список записей.
     * @return список записей.
     */
    @XmlElement(name = "Entry")
    public List<Entry> getEntryList() {
        return this.list;
    }

    /**
     * Устанавливает список записей.
     * @param list список записей.
     */
    public void setEntryList(List<Entry> list) {
        this.list = list;
    }
}
