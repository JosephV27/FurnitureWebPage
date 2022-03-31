package ac.cr.itcr.furniture_management;


import ac.cr.itcr.furniture_management.repositories.EmployeeRepository;
import ac.cr.itcr.furniture_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class FurnitureManagementApplication implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(FurnitureManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//         Optional<Employee> employee = employeeRepository.findById(2);
//         System.out.println(employee.get().getName());
    }
}
