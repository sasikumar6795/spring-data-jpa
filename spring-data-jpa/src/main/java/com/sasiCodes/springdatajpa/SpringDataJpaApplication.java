package com.sasiCodes.springdatajpa;

import com.github.javafaker.Faker;
import com.sasiCodes.springdatajpa.model.*;
import com.sasiCodes.springdatajpa.repository.StudentIdCardRepository;
import com.sasiCodes.springdatajpa.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);

			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));

			student.addBook(
					new Book("Clean Code", LocalDateTime.now().minusDays(4)));


			student.addBook(
					new Book("Think and Grow Rich", LocalDateTime.now()));


			student.addBook(
					new Book("Spring Data JPA", LocalDateTime.now().minusYears(1)));

			StudentIdCard studentIdCard = new StudentIdCard(
					"123456789",
					student);

			student.setStudentIdCard(studentIdCard);
           // studentIdCardRepository.save(studentIdCard);

//			student.enrolToCourse(new Course("springboot", "IT"));
//			student.enrolToCourse(new Course("DataBaseDesign", "IT"));

			student.addEnrollment(new Enrollment(new EnrollmentId(1L,1L),
					student,new Course("springboot", "IT"),LocalDateTime.now()));

			student.addEnrollment(new Enrollment(new EnrollmentId(1L,2L),
					student,new Course("Java", "IT"),LocalDateTime.now().minusYears(1)));

			studentRepository.save(student);

//			studentIdCardRepository.findById(1L).ifPresent(
//					System.out::println
//			);

			List<Student> listOfStudents = studentRepository.findAll();

			Optional.ofNullable(studentIdCardRepository.findById(1L)).orElseThrow(() -> new IllegalStateException("no student id card found"));

			Optional.ofNullable(listOfStudents.stream()
					.filter(studentAge -> studentAge.getAge() >18)
					.sorted(Comparator.comparing(Student::getFirstName))
					.collect(Collectors.toList()));

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

			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));
			studentRepository.save(student);
		}
	}

}
