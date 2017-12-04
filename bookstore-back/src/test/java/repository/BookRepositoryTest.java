package repository;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import rest.BookEndPoint;
import rest.JAXRSConfiguration;
import rest.repository.BookRepository;
import util.IsbnGenerator;
import util.NumberGenerator;
import util.TextUtil;

import javax.inject.Inject;

import static org.junit.Assert.*;


import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BookRepositoryTest {

    private static Long bookId;

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private BookRepository bookRepository;

    // ======================================
    // =             Deployment             =
    // ======================================

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(BookRepository.class)
                .addClass(TextUtil.class)
                .addClass(NumberGenerator.class)
                .addClass(IsbnGenerator.class)
                .addClass(BookEndPoint.class)
                .addClass(JAXRSConfiguration.class)


                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

    @Test
    @InSequence(1)
    public void shouldBeDeployed() {
        assertNotNull(bookRepository);
    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void countAll() throws Exception {
    }

    @Test
    public void create() throws Exception {
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

        Book book = new Book("isbn", "a    title", 12f, 123, Language.DEUTSCH, new Date(), "http://blah", "dssds");
        bookRepository.create(book);

        Long bookId = book.getId();
        // Checks the created book
        assertNotNull(book);

        //find booke we just created
        Book bookFoud = bookRepository.find(bookId);
        //check ID
        // assertNotNull(book.getId());

        assertEquals(bookFoud.getTitle(), "a title");
        assertTrue(bookFoud.getIsbn().startsWith("13"));

        assertEquals(Long.valueOf(1), bookRepository.countAll());
        assertEquals(1, bookRepository.findAll().size());

        bookRepository.delete(bookId);

        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

    }

    @Test
    public void delete() throws Exception {
    }

    @Test(expected = Exception.class)
    public void createInvalidBook() {

        Book book = new Book("isbn", null, 12f, 123, Language.DEUTSCH, new Date(), "http://blah", "dssds");
        bookRepository.create(book);

    }

    @Test(expected = Exception.class)
    public void findWithInvalidID() {
        bookRepository.find(null);
    }

}