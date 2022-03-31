package com.sasiCodes.springdatajpa.model;

import com.sasiCodes.springdatajpa.Enrollment;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity(name="Course")
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize =1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(name="id", updatable =false)
    private Long id;

    @Column(name="course_name", nullable = false)
    private String courseName;

    @Column(name="department", nullable = false)
    private String department;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy="course"
    )
    private List<Enrollment> enrollments = new ArrayList<>();

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }


    public void addEnrollment(Enrollment enrollment) {
        if(!(this.enrollments.contains(enrollment)))
        {
            this.enrollments.add(enrollment);
        }
    }

    public void removeEnrollment(Enrollment enrollment) {
        if(this.enrollments.contains(enrollment))
        {
            this.enrollments.remove(enrollment);
        }
    }

    //    @ManyToMany(mappedBy="courses")
//    private List<Student> students = new ArrayList<>();


//    public List<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }

    public Course(){
    }

    public Course(String courseName, String department) {
        this.courseName = courseName;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
