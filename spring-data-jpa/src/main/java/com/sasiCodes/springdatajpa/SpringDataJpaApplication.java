package com.sasiCodes.springdatajpa;

import com.github.javafaker.Faker;
import com.sasiCodes.springdatajpa.model.Student;
import com.sasiCodes.springdatajpa.model.StudentIdCard;
import com.sasiCodes.springdatajpa.repository.StudentIdCardRepository;
import com.sasiCodes.springdatajpa.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository)
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

		return args -> {
			//generateRandomStudents(studentRepository);
			//studentRepository.findAll(Sort.by(Sort.Direction.ASC,"firstName"))
			//sorting(studentRepository);
            //pagination(studentRepository);
//            Faker faker = new Faker();
//            String firstName = faker.name().firstName();
//            String lastName = faker.name().lastName();
//            String email = String.format("%s.%s@gmail.com", firstName, lastName);
//
//            Student student = Student.builder()
//                    .firstName(firstName)
//                    .lastName(lastName)
//                    .email(email)
//                    .age(faker.number().numberBetween(17,55))
//                    .build();
//
//            StudentIdCard studentIdCard = StudentIdCard.builder().cardNumber("123456789")
//                    .student(student).build();
//
//            studentIdCardRepository.save(studentIdCard);

			studentIdCardRepository.findById(1L).ifPresent(
					System.out::println
			);

        };
	}

    private void pagination(StudentRepository studentRepository) {
        PageRequest pageRequest = PageRequest.of(0,5,Sort.by("firstName").descending());
        Page<Student> page = studentRepository.findAll(pageRequest);
        System.out.println(page);
    }

    private void sorting(StudentRepository studentRepository) {
		studentRepository.findAll(Sort.by("firstName").descending().and(Sort.by("age").descending()))
				.forEach(student -> {
					System.out.println(student.getFirstName() + " " + student.getAge());
				});
	}

	private void generateRandomStudents(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for (int i = 0; i <=20 ; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);

			Student student = Student.builder()
					.firstName(firstName)
					.lastName(lastName)
					.email(email)
					.age(faker.number().numberBetween(17,55))
					.build();
			studentRepository.save(student);
		}
	}

}
