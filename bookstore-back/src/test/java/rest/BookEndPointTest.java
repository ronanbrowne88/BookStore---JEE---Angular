package rest;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import rest.repository.BookRepository;
import util.IsbnGenerator;
import util.NumberGenerator;
import util.TextUtil;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@RunAsClient
public class BookEndPointTest {

    private Response response;


    @Deployment(testable = false)
    public static Archive<?> createDeploymentPackage() {

        return ShrinkWrap.create(WebArchive.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(BookRepository.class)
                .addClass(NumberGenerator.class)
                .addClass(IsbnGenerator.class)
                .addClass(TextUtil.class)
                .addClass(BookEndPoint.class)
                .addClass(JAXRSConfiguration.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
    }


    @Test
    public void createBook(@ArquillianResteasyResource("api/books")WebTarget webTarget) {

        //test count
        response = webTarget.path("count").request().get();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode() ,response.getStatus());

        //test find all
        response = webTarget.request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode() ,response.getStatus());

        //create a book
        Book book = new Book("isbn", " dsd " , 12f, 123, Language.DEUTSCH, new Date(), "http://blah", "dssds");
        response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(book, MediaType.APPLICATION_JSON ));
        assertEquals(Response.Status.CREATED.getStatusCode() ,response.getStatus());


    }


}
