package syksy2024.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//Source for validation int: https://stackoverflow.com/questions/41749278/spring-validation-of-integer-attribute

@Entity
@Table(name="Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Book title is required")
    @Size(min=2, max=50)
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "Author name is required")
    @Size(min=2, max=50)
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull
    @Min(1)
    @Max(2024)
    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Size(min=2, max=50)
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    
    public Book() {
    }


    public Book(String title, String author, int publicationYear, String isbn, double price, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public int getPublicationYear() {
        return publicationYear;
    }


    public String getIsbn() {
        return isbn;
    }


    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + ", ISBN=" + isbn
                + ", price=" + price + "]";
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    

    

}
