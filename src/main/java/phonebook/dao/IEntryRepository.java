package phonebook.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import phonebook.models.Entry;

/**
 * Interface IEntryRepository extends CrudRepository.
 *
 * @author Multiscripter
 * @version 2019-09-29
 * @since 2018-09-29
 */
@Repository
public interface IEntryRepository extends CrudRepository<Entry, Long> {
    /**
     * Deletes entry by identifier.
     * @param id entry`s identifier.
     */
    void deleteById(long id);

    /**
     * Finds all entries.
     * @return entry list.
     */
    List<Entry> findAll();

    /**
     * Finds entries by last name.
     * @param lastName entries by last name.
     * @return entry list.
     */
    List<Entry> findByLastName(String lastName);

    /**
     * Finds entries by phone number.
     * @param number phone number.
     * @return entry list.
     */
    List<Entry> findByNumber(int number);

    /**
     * Saves entry in repository.
     * @param entry to save.
     * @return saved entry.
     */
    Entry save(Entry entry);
}
