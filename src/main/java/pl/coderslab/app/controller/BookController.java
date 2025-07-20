package pl.coderslab.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.entity.Book;

@Controller
@RequestMapping("/book")
@ResponseBody
public class BookController {

    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @PostMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam("title") String title,
                      @RequestParam("rating") int rating,
                      @RequestParam("description") String description) {
        Book book = new Book();
        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);
        bookDao.save(book);
        return "Dodano książkę o ID: " + book.getId();
    }

    @PostMapping(value = "/update", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String update(@RequestParam("id") long id,
                         @RequestParam("title") String title,
                         @RequestParam("rating")int rating,
                         @RequestParam("description") String description) {
        Book book = bookDao.findById(id);
        if (book == null) {
            return "Nie znaleziono książki o ID: " + id;
        }
        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);
        bookDao.update(book);
        return "Zaktualizowano książkę o ID: " + id;
    }

    @GetMapping(value = "/get", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String get(@RequestParam("id") Long id) {
        Book book = bookDao.findById(id);
        if (book == null) {
            return "Nie znaleziono książki o ID: " + id;
        }
        return book.toString();
    }

    @PostMapping(value = "/delete", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("id") Long id) {
        bookDao.delete(id);
        return "Usunięto książkę o ID: " + id + " (jeśli istniała)";
    }
}
