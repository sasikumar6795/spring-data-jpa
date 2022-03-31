package com.sasiCodes.springdatajpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Book")
@Table(name = "book")
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize =1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name="id", updatable =false)
    private Long id;

    @Column(name="book_name", nullable = false)
    private String bookName;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "student_book_student_id_fk"
            )
    )
    private Student student;

    public Book(String bookName, LocalDateTime createAt) {
        this.bookName = bookName;
        this.createAt = createAt;
    }

    public Student getStudent() {
        return student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", createAt=" + createAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, createAt, id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
        {
            return true;
        }
        if(!(obj instanceof Book))
        {
            return false;
        }
        return id!=null&&id.equals(((Book) obj).getId());
    }
}
