package com.sasiCodes.springdatajpa;

import com.sasiCodes.springdatajpa.model.Course;
import com.sasiCodes.springdatajpa.model.EnrollmentId;
import com.sasiCodes.springdatajpa.model.Student;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Enrollment")
@Table(name="enrollment")
public class Enrollment {


    @EmbeddedId
    private EnrollmentId enrollmentId;


    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id",
            foreignKey = @ForeignKey(
                    name = "enrollment_student_id_fk"
            )
    )
    private Student student;



    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id",
            foreignKey = @ForeignKey(
                    name = "enrollment_course_id_fk"
            )
    )
    private Course course;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createAt;

    public Enrollment() {
    }

    public Enrollment(EnrollmentId enrollmentId, Student student, Course course, LocalDateTime createAt) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.createAt = createAt;
    }


    public EnrollmentId getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(EnrollmentId enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", student=" + student +
                ", course=" + course +
                '}';
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
