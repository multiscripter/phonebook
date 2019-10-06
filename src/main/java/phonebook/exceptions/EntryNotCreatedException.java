package phonebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class EntryNotCreatedException realizes exception "Entry not created".
 *
 * @author Multiscripter
 * @version 2019-10-03
 * @since 2017-10-03
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EntryNotCreatedException extends Exception {
    /**
     * Constructor.
     */
    public EntryNotCreatedException() {
        super("Entry not created");
    }
}
