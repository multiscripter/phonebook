package phonebook.services;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import phonebook.models.Entry;

/**
 * Interface EntryRepository extends CrudRepository.
 * @version 2019-09-19
 * @since 2018-09-19
 */
@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
    /**
     * Find entries by last name.
     * @param lastName entries by last name.
     * @return entries list.
     */
    List<Entry> findByLastName(String lastName);

    /**
     * Find entries by phone number.
     * @param number phone number.
     * @return entries list.
     */
    List<Entry> findByNumber(int number);
}
