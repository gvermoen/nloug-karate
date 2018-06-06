package nl.oug.hr.hrservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@SpringBootApplication
class HrServiceApplication

fun main(args: Array<String>) {
    runApplication<HrServiceApplication>(*args)
}

data class Department(val departmentId: Int, var departmentName: String, var managerId: Int?, var locationId: Int)

interface DepartmentRepository: ReactiveMongoRepository<Department, Int>

@RestController
@RequestMapping("/api/departments")
class DepartmentRestController(private val departmentRepository: DepartmentRepository) {

    @GetMapping
    fun getAllDepartments() = departmentRepository.findAll()

    @PostMapping
    fun createDepartment(@RequestBody @Valid department: Department) = departmentRepository.save(department)
}