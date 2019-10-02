package phonebook.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import phonebook.models.Entry;
import phonebook.models.EntryList;
import phonebook.services.EntryRepository;

/**
 * Класс Router реализует маршрутизатор запросов.
 * @author Multiscripter
 * @version 2019-10-01
 * @since 2017-10-01
 */
@RestController
public class Router {
    /**
     * Список работников.
     */
    @Autowired
    private EntryList list;

    /**
     * Репозиторий записей.
     */
    @Autowired
    private EntryRepository repoEntry;

    /**
     * Обрабатывает GET-запросы к URI /get-entry/1/.
     * curl http://localhost:8080/get-entry/1/
     * @param id идентификатор записи.
     * @return запись.
     */
    @GetMapping("/get-entry/{id}/")
    public @ResponseBody Entry getEntry(@PathVariable long id) {
        return repoEntry.findById(id).get();
    }

    /**
     * Обрабатывает GET-запросы к URI /get-entries/.
     * Получает список записей из базы.
     * @return список записей.
     */
    @GetMapping("/get-entries/")
    public @ResponseBody EntryList getEntries() {
        List<Entry> list = new ArrayList<>();
        repoEntry.findAll().forEach(list::add);
        this.list.setEntryList(list);
        return this.list;
    }
}
