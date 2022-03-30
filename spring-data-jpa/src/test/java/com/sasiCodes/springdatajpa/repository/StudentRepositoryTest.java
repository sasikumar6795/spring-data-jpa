package com.sasiCodes.springdatajpa.repository;

import com.sasiCodes.springdatajpa.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    private StudentRepository underTest;

    @Test
    void findStudentByEmail() {
        //given
//        Student student = Student.builder()
//                .email("sasi@gmail.com")
//                .age(18)
//                .build();
        //when
      //  underTest.save(student);
        //then
    }
}