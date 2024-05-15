import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book book = new Book("Esprit Mta3 NAM",1999,"Taher Bel Lakhdher");
        Book book1 = new Book("Esprit Mta3 Zab",1999,"Taher Bel Lakhdher");
        Book book2 = new Book("Esprit Mta3 Elli t7eb Enti",1999,"Taher Bel Lakhdher");
        Library library = new Library();
        List<Media> books = List.of(book,book1,book2);
        library.addMedia(books);
        System.out.println(books);


    }
}