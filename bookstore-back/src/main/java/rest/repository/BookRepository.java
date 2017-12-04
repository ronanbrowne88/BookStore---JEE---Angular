package rest.repository;

import com.pluralsight.bookstore.model.Book;
import util.NumberGenerator;
import util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;


//repository facade to handle books

@Transactional(Transactional.TxType.SUPPORTS)
// inherets from caller transaction suits read only , can be on methods or class
public class BookRepository {

    @Inject
    private TextUtil textUtil;

    @Inject
    private NumberGenerator generator;

    @PersistenceContext(unitName = "bookStorePU")
    public EntityManager em;

    //fist 3 methods are read only
    public Book find(@NotNull Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b from Book b ORdER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("SELECT count(b) from Book b", Long.class);
        return query.getSingleResult();
    }

    //Write only methods

    @Transactional(Transactional.TxType.REQUIRED) //always starts a new transaction
    public Book create(@NotNull Book book) {
        book.setTitle(textUtil.sanitize(book.getTitle()));
        book.setIsbn(generator.generateNumber());
        em.persist(book);
        return book;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(@NotNull Long id) {
        em.remove(em.getReference(Book.class, id));
    }


}
