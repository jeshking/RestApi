package payroll;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }
@GetMapping("/employees")
List<Employee> getAllEmployees() {
    return repository.findAll();
}
@PostMapping("/employees")
Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

@GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
@PutMapping("/employees/{id}")
    Employee replaeEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    return repository.findById(id)
            .map(employee -> {
                employee.setName(newEmployee.getName());
                employee.setRole(newEmployee.getRole());
                return repository.save(employee);
            })
            .orElseGet(() -> {
                newEmployee.setId(id);
                return repository.save(newEmployee);
            });
    }


}
