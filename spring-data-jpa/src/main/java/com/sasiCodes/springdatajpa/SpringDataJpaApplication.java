package com.sasiCodes.springdatajpa;

import com.sasiCodes.springdatajpa.model.Student;
import com.sasiCodes.springdatajpa.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository)
	{


		/*return args -> {

			Student maria = Student.builder()
					.firstName("maria")
					.lastName("sen")
					.email("maria@gmail.com")
					.age(21)
					.build();

			Student maria2 = Student.builder()
					.firstName("maria2")
					.lastName("sen")
					.email("maria2@gmail.com")
					.age(18)
					.build();

			Student ahmed = Student.builder()
					.firstName("ahmed")
					.lastName("ulla")
					.email("ahmed@gmail.com")
					.age(18)
					.build();
			studentRepository.saveAll(List.of(maria,ahmed,maria2));

			System.out.println(studentRepository.count());
			studentRepository.findById(2L).ifPresentOrElse(student -> {
				System.out.println(student.getFirstName());
			}, () -> {
				throw new IllegalStateException("student not found");
			});

			studentRepository.findStudentsByFirstNameAndAgeNative("maria", 21).forEach(
					System.out::println
			);

			System.out.println(studentRepository.deleteStudentById(1L));
		};*/

		return args -> {};
	}

}
