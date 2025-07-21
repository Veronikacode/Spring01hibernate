package pl.coderslab.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dao.AuthorDao;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.dao.PublisherDao;
import pl.coderslab.app.entity.Author;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Category;
import pl.coderslab.app.entity.Publisher;
import pl.coderslab.app.repository.BookRepository;
import pl.coderslab.app.repository.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping("/book")
@ResponseBody
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryrepository;

    @Autowired
    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, BookRepository bookRepository, CategoryRepository categoryrepository) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.bookRepository = bookRepository;
        this.categoryrepository = categoryrepository;
    }

    @GetMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add() {
        Publisher publisher = new Publisher();
        publisher.setName("Test Publisher");
        publisherDao.save(publisher);

//        usunięte na potrzeby przyszłych zadań
//        Author author1 = authorDao.findById(1L);
//        Author author2 = authorDao.findById(2L);
//
//        if (author1 == null || author2 == null) {
//            return "Nie znaleziono autorów o podanych ID.";
//        }

        Book book = new Book();
        book.setTitle("title");
        book.setRating(5);
        book.setDescription("Test description");
        book.setPublisher(publisher);

//        book.setAuthors(List.of(author1, author2));

        bookDao.save(book);
        return "Dodano książkę o ID: " + book.getId();
    }

    @GetMapping("/all")
    @ResponseBody
    public String getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.toString();

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

    @GetMapping("/byTitle")
    @ResponseBody
    public String bookByTitle() {
        Publisher publisher = new Publisher();
        publisher.setName("Test Publisher");
        publisherDao.save(publisher);

        Book book = new Book();
        String title = book.setTitle("Test Title");
        bookRepository.save(book);

        Book testTitle = bookRepository.findByTitle("Test Title");
        return testTitle.toString();
    }

    @GetMapping("/byCategory")
    @ResponseBody
    public String bookByCategory() {
        Publisher publisher = new Publisher();
        publisher.setName("Test Publisher");
        publisherDao.save(publisher);

        Category category = new Category();
        category.setName("Test Category");
        categoryrepository.save(category);

        Book book = new Book();
        book.setTitle("Test Book");
        book.setCategory(category);
        bookRepository.save(book);

        List<Book> byCategory = bookRepository.findByCategory(category.getName());

        return byCategory.toString();

    }
}
