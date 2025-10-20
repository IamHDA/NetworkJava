package UDP;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 20251107L;
    String id, title, author, isbn, publishDate;

    public Book(String id, String title, String author, String isbn, String publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
