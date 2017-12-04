package rest;

import com.pluralsight.bookstore.model.Book;
import io.swagger.annotations.*;
import rest.repository.BookRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.security.PrivateKey;
import java.util.List;

@SwaggerDefinition(
        info = @Info(
                title = "BookStore APIs",
                description = "BookStore APIs exposed from a Java EE back-end to an Angular front-end",
                version = "V1.0.0",
                contact = @Contact(
                        name = "Antonio Goncalves",
                        email = "antonio.goncalves@gmail.com",
                        url = "https://app.pluralsight.com/library/search?q=Antonio+Goncalves"
                )
        ),
        host = "localhost:8080",
        basePath = "/bookstore-back/api",
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        tags = {
                @Tag(name = "Book", description = "Tag used to denote operations as private")
        }
)
@Path("/books")
@Api
public class BookEndPoint {

    @Inject
    private BookRepository bookRepository;

    @GET
    @Path("{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns a Book given a id", response = Book.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Book found"),
            @ApiResponse(code = 400, message = "Invalid input. Id cannot be lower than 1"),
            @ApiResponse(code = 404, message = "Book not found")

    })
    public Response getBook(@PathParam("id") @Min(1) Long id) {
        Book book = bookRepository.find(id);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(book).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns all the books", response = Book.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Books found"),
            @ApiResponse(code = 204, message = "No books found"),
    })
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.size() == 0)
            return Response.noContent().build();

        return Response.ok(books).build();
    }


    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Returns the number of books", response = Long.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Number of books found"),
            @ApiResponse(code = 204, message = "No books found"),
    })
    public Response countBooks() {
        Long countAll = bookRepository.countAll();

        if (countAll == 0)
            return Response.noContent().build();

        return Response.ok(countAll).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation("Creates a book given a JSon Book representation")
    @ApiResponses({
            @ApiResponse(code = 201, message = "The book is created"),
            @ApiResponse(code = 415, message = "Format is not JSon")
    })
    public Response createBook(@ApiParam(value = "Book to be created", required = true) Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }


    @DELETE
    @Path("/{id : \\d+}")
    @ApiOperation("Deletes a book given an id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Book has been deleted"),
            @ApiResponse(code = 400, message = "Invalid input. Id cannot be lower than 1"),
            @ApiResponse(code = 500, message = "Book not found")
    })
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }


}
