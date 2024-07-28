package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class BootstrapData implements CommandLineRunner {


    // Wires the repositories to be used
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    // Needed to create Objects
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Creating First Author
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        // Creating First Book
        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        // Saving Author and Book to repository
        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        // Create a second Author
        Author brando = new Author();
        brando.setFirstName("Brando");
        brando.setLastName("Bando");

        // Create a second Book
        Book bandoBio = new Book();
        bandoBio.setTitle("Amazing Tale of Brando Bando");
        bandoBio.setIsbn("777777");

        // Save Author and Book to Repository
        Author brandoSaved = authorRepository.save(brando);
        Book bandoSaved = bookRepository.save(bandoBio);

        // Crete Association between books
        ericSaved.getBooks().add(dddSaved);
        brandoSaved.getBooks().add(bandoSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count:" + authorRepository.count());
        System.out.println("Book Count:" + bookRepository.count());


    }
}
