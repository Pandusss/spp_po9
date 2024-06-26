import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Catalog {
    private List<Book> books;

    public Catalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public class IssueHistory {
        private Book book;
        private List<String> readers;
        private List<Date> dates;

        public IssueHistory(Book book) {
            this.book = book;
            readers = new ArrayList<>();
            dates = new ArrayList<>();
        }

        public void issueBook(String reader, Date date) {
            readers.add(reader);
            dates.add(date);
        }

        public void displayIssueHistory() {
            for (int i = 0; i < readers.size(); i++) {
                System.out.println("Читатель: " + readers.get(i) + ", Дата: " + dates.get(i));
            }
        }
    }
}

class Book {
    private String title;
    private String author;
    private int pageCount;

    public Book(String title, String author, int pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Заголовок: " + title + ", Автор: " + author + ", Количество страниц: " + pageCount;
    }
}

public class Main1 {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        Book book1 = new Book("1", "Зеленков", 248);
        Book book2 = new Book("2", "Марзан", 192);

        catalog.addBook(book1);
        catalog.addBook(book2);

        Catalog.IssueHistory issueHistory1 = catalog.new IssueHistory(book1);
        issueHistory1.issueBook("Дмитрий Кухарев", new Date());
        issueHistory1.issueBook("Никита Зейденс", new Date());

        Catalog.IssueHistory issueHistory2 = catalog.new IssueHistory(book2);
        issueHistory2.issueBook("Ярослав Кучко", new Date());

        System.out.println("Книги:");
        catalog.displayBooks();

        System.out.println("\nИстория книги 1:");
        issueHistory1.displayIssueHistory();

        System.out.println("\nИстория книги 2:");
        issueHistory2.displayIssueHistory();
    }
}
