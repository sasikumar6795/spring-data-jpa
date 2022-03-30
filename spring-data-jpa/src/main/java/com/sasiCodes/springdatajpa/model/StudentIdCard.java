package com.sasiCodes.springdatajpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="Student_id_card")
@Table(name="student_id_card", uniqueConstraints = {
        @UniqueConstraint(name ="student_id_card_number_unique",columnNames = "card_number")

})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentIdCard {
    @Id
    @SequenceGenerator(name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize =1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence"
    )
    @Column(name="id", updatable =false)
    private Long id;

    @Column(name="card_number",nullable = false, length = 15)
    private String cardNumber;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_card_student_id_fk"
            )
    )
    private Student student;

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student=student;

    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
