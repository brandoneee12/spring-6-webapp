package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class BootstrapData implements CommandLineRunner {


    // Wires the repositories to be used
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;



    // Needed to create Objects
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
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

        // Crete Association between books. Adds Author to book
        ericSaved.getBooks().add(dddSaved);
        brandoSaved.getBooks().add(bandoSaved);
        bandoSaved.getAuthors().add(brandoSaved);
        dddSaved.getAuthors().add(ericSaved);


        // Creating a Publisher
        Publisher pub1 = new Publisher();
        pub1.setPublisherName("BrandoBando");
        pub1.setAddress("12 RandomAddress");
        pub1.setCity("Christiana");
        pub1.setState("Delaware");
        pub1.setZip("19777");
        Publisher savedPublisher = publisherRepository.save(pub1);

        // Setting a publisher for both books
        dddSaved.setPublisher(savedPublisher);
        bandoSaved.setPublisher(savedPublisher);

        // Saving to Repositories
        authorRepository.save(ericSaved);
        authorRepository.save(brandoSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(bandoSaved);


        // Printing out the count inside our repositories
        System.out.println("In Bootstrap");
        System.out.println("Author Count:" + authorRepository.count());
        System.out.println("Book Count:" + bookRepository.count());



        publisherRepository.save(pub1);
        System.out.println("Publisher Count:" + publisherRepository.count());



    }
}
