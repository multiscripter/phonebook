package phonebook.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import phonebook.exceptions.EntryNotCreatedException;
import phonebook.models.Entry;
import phonebook.models.EntryList;
import phonebook.services.EntryService;

/**
 * Class Router realize request router.
 *
 * @author Multiscripter
 * @version 2019-10-06
 * @since 2017-10-01
 */
@RestController
public class Router {
    /**
     * Entry list.
     */
    @Autowired
    private EntryList list;

    /**
     * Entry repository.
     */
    @Autowired
    private EntryService entryService;

    /**
     * Handles POST requests to URI /create-entry/.
     * Creates a record in the database.
     * The answer has no body. Headers only.
     * Example:
     * JSON-request:
     * curl http://localhost:8080/create-entry/ -X POST -H "Accept: application/json" -H "Content-Type: application/json" -d '{"id":0,"number":7900100,"lastName":"Безфамильный","firstName":"Безымянный","middleName":"Батинету","address":"Мой адрес не дом и не улица"}'
     * @param entry json or xml with entry.
     * @throws EntryNotCreatedException exception "Entry not created".
     */
    @PostMapping("/create-entry/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEntry(@RequestBody Entry entry) throws EntryNotCreatedException {
        try {
            this.entryService.save(entry);
        } catch (Exception ex) {
            throw new EntryNotCreatedException();
        }
    }

    /**
     * Handles DELETE requests to URI /delete-entry/{id}/.
     * The answer has no body. Headers only.
     * curl http://localhost:8080/delete-entry/1/ -X DELETE
     * @param id entry identifier.
     */
    @DeleteMapping("/delete-entry/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntry(@PathVariable long id) {
        this.entryService.deleteById(id);
    }

    /**
     * Handles GET requests to URI /get-entry/%d/.
     * Returns entry as response.
     * curl http://localhost:8080/get-entry/1/
     * @param id entry identifier.
     * @return entry.
     */
    @GetMapping("/get-entry/{id}/")
    public @ResponseBody Entry getEntry(@PathVariable long id) {
        return entryService.findById(id);
    }

    /**
     * Handles GET requests to URI /get-entries/.
     * Returns entry list as response.
     * curl http://localhost:8080/get-entries/
     * @return entry list.
     */
    @GetMapping("/get-entries/")
    public @ResponseBody EntryList getEntries() {
        List<Entry> list = new ArrayList<>();
        entryService.findAll().forEach(list::add);
        this.list.setEntryList(list);
        return this.list;
    }

    /**
     * Handles PUT requests to URI /update-entry/{id}/.
     * @param id id entry identifier.
     * @param entry entry json or xml with entry.
     */
    @PutMapping("/update-entry/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void updateEntry(@PathVariable long id, @RequestBody Entry entry) {
        this.entryService.save(entry);
    }
}
