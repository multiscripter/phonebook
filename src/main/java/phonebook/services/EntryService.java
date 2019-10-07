package phonebook.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phonebook.dao.IEntryRepository;
import phonebook.models.Entry;

/**
 * Class EntryService realizes entity "Entry service".
 *
 * @author Multiscripter
 * @version 2019-10-07 Repository injection moved to constructor.
 * @version 2019-10-06
 * @since 2018-10-05
 */
@Service
public class EntryService {
    /**
     * Entry repository.
     */
    private IEntryRepository repository;

    /**
     * Constructor.
     * @param repository entry repository.
     */
    @Autowired
    public EntryService(IEntryRepository repository) {
        this.repository = repository;
    }

    /**
     * Deletes entry by identifier.
     * @param id entry identifier.
     */
    public void deleteById(long id) {
        this.repository.deleteById(id);
    }

    /**
     * Finds all entries.
     * @return entry list.
     */
    public List<Entry> findAll() {
        return this.repository.findAll();
    }

    /**
     * Finds entry by its identifier.
     * @param id entry identifier.
     * @return found entry.
     */
    public Entry findById(Long id) {
        return this.repository.findById(id).get();
    }

    /**
     * Finds entries by last name.
     * @param lastName entries by last name.
     * @return entry list.
     */
    public List<Entry> findByLastName(String lastName) {
        return this.repository.findByLastName(lastName);
    }

    /**
     * Finds entries by phone number.
     * @param number phone number.
     * @return entry list.
     */
    public List<Entry> findByNumber(int number) {
        return this.repository.findByNumber(number);
    }

    /**
     * Saves entry in repository.
     * @param entry to save.
     * @return saved entry.
     */
    public Entry save(Entry entry) {
        return this.repository.save(entry);
    }
}
