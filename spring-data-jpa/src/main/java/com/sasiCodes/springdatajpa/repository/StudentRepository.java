package com.sasiCodes.springdatajpa.repository;

import com.sasiCodes.springdatajpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select s from Student s where s.email =?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("select s from Student s where s.firstName=?1 and s.age=?2")
    List<Student> findStudentsByFirstNameAndAge(String firstName, Integer age);

    @Query(value = "select * from Student where first_name=?1 and age=?2", nativeQuery = true)
    List<Student> findStudentsByFirstNameAndAgeNative(String firstName, Integer age);

    @Modifying
    @Transactional
    @Query("DELETE from Student where id=?1")
    int deleteStudentById(Long id);
}
