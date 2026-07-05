import java.util.Arrays;
import java.util.Comparator;

public class LibraryManagementDemo {
    public static void main(String[] args) {
        Book[] books = {
                new Book(1, "Clean Code", "Robert Martin"),
                new Book(2, "Algorithms", "Robert Sedgewick"),
                new Book(3, "Java Basics", "James Gosling")
        };

        BookSearch bookSearch = new BookSearch();
        System.out.println(bookSearch.linearSearchByTitle(books, "Java Basics"));
        System.out.println(bookSearch.linearSearchByAuthor(books, "Robert Martin"));

        Arrays.sort(books, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        System.out.println(bookSearch.binarySearchByTitle(books, "Algorithms"));
    }
}
