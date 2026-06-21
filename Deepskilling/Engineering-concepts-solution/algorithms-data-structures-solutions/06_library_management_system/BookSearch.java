public class BookSearch {
    public Book linearSearchByTitle(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        return null;
    }

    public Book binarySearchByTitle(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = books[middle].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[middle];
            }

            if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return null;
    }

    public Book linearSearchByAuthor(Book[] books, String author) {
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }

        return null;
    }
}
