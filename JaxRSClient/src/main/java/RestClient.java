import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class RestClient {

    public static void main(String[] args) {



        Client client = ClientBuilder.newClient();


        String uri = "http://localhost:8080/bookstore-back/api/books/count";

//       String x =  client
//                .target(uri)
//               .request(MediaType.TEXT_PLAIN)
//               .get(String.class);

        String uriGetBook1 = "http://localhost:8080/bookstore-back/api/books/1001";


        String x1 =  client
                .target(uriGetBook1)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd");
        Gson gson = builder.create();

        Book book= gson.fromJson(x1, Book.class);


        System.out.println(book.getTitle());



    }


}
