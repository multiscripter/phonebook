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
 * @version 2019-10-05
 * @since 2018-10-05
 */
@Service
public class EntryService {
    /**
     * Entry repository.
     */
    @Autowired
    private IEntryRepository repository;

    /**
     * Finds entry by its identifier.
     * @param id entry identifier.
     * @return found entry.
     */
    public Entry findById(Long id) {
        return this.repository.findById(id).get();
    }

    /**
     * Finds all entries.
     * @return entry list.
     */
    public List<Entry> findAll() {
        return this.repository.findAll();
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
