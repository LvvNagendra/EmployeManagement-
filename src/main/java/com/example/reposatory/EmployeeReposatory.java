package com.example.reposatory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;


@Repository
public interface EmployeeReposatory extends JpaRepository<Employee, Long> {
	public List<Employee> getByEmployeeId(long employeeId);
	 Employee findByEmployeeName(String employeeName);

}
