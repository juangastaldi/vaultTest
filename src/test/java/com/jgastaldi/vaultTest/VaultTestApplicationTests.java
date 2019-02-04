package com.jgastaldi.vaultTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgastaldi.vaultTest.model.Country;
import com.jgastaldi.vaultTest.model.Department;
import com.jgastaldi.vaultTest.model.Employee;
import com.jgastaldi.vaultTest.model.Job;
import com.jgastaldi.vaultTest.model.Location;
import com.jgastaldi.vaultTest.model.Region;
import com.jgastaldi.vaultTest.repository.CountryRepository;
import com.jgastaldi.vaultTest.repository.DepartmentRepository;
import com.jgastaldi.vaultTest.repository.EmployeeRepository;
import com.jgastaldi.vaultTest.repository.JobRepository;
import com.jgastaldi.vaultTest.repository.LocationRepository;
import com.jgastaldi.vaultTest.repository.RegionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VaultTestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private JobRepository jobRepository;

	@Before
	public void setUp() {
		Region region = new Region();
		region.setRegionName("America Latina");
		regionRepository.save(region);

		Country country = new Country();
		country.setCountryName("Argentina");
		country.setRegion(region);
		countryRepository.save(country);

		Location location = new Location();
		location.setCity("Capital Federal");
		location.setPostalCode("1018");
		location.setStateProvince("Capital Federal");
		location.setStreetAddress("calle 1234");
		location.setCountry(country);
		locationRepository.save(location);

		Department department = new Department();
		department.setLocation(location);
		department.setDepartmentName("Departamento");
		departmentRepository.save(department);

		Job job = new Job();
		job.setJobTitle("Manager");
		job.setMaxSalary(2000L);
		job.setMinSalary(1000L);
		jobRepository.save(job);
	}

	@Test
	public void createEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setCommissionPct(2.3);
		employee.setEmail("email@email.com");
		employee.setFirstName("Nombre");
		employee.setLastName("apellido");
		employee.setDepartment(departmentRepository.findById(1L).get());
		employee.setJob(jobRepository.findById(1L).get());
		employee.setPhoneNumber("1234567");
		employee.setSalary(1700.0);
		LocalDate now = LocalDate.now();
		employee.setHireDate(now);

		MvcResult result = this.mockMvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee))).andExpect(status().isOk()).andReturn();

		Employee returnEmployee = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);

		Employee createdEmployee = employeeRepository.findById(returnEmployee.getId()).orElse(null);
		assert (employee.getCommissionPct().equals(createdEmployee.getCommissionPct()));
		assert (employee.getFirstName().equals(createdEmployee.getFirstName()));
		assert (employee.getLastName().equals(createdEmployee.getLastName()));
		assert (employee.getDepartment().getId().equals(createdEmployee.getDepartment().getId()));
		assert (employee.getJob().getId().equals(createdEmployee.getJob().getId()));
		assert (employee.getPhoneNumber().equals(createdEmployee.getPhoneNumber()));
		assert (employee.getSalary().equals(createdEmployee.getSalary()));
		assert (employee.getHireDate().equals(now));

		employeeRepository.deleteById(createdEmployee.getId());
	}

	@Test
	public void findEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setCommissionPct(2.3);
		employee.setEmail("email@email.com");
		employee.setFirstName("Nombre");
		employee.setLastName("apellido");
		employee.setDepartment(departmentRepository.findById(1L).get());
		employee.setJob(jobRepository.findById(1L).get());
		employee.setPhoneNumber("1234567");
		employee.setSalary(1700.0);
		LocalDate now = LocalDate.now();
		employee.setHireDate(now);

		employee = employeeRepository.save(employee);

		MvcResult result = this.mockMvc
				.perform(get("/api/employee/" + employee.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		Employee createdEmployee = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);
		assert (employee.getCommissionPct().equals(createdEmployee.getCommissionPct()));
		assert (employee.getFirstName().equals(createdEmployee.getFirstName()));
		assert (employee.getLastName().equals(createdEmployee.getLastName()));
		assert (employee.getDepartment().getId().equals(createdEmployee.getDepartment().getId()));
		assert (employee.getJob().getId().equals(createdEmployee.getJob().getId()));
		assert (employee.getPhoneNumber().equals(createdEmployee.getPhoneNumber()));
		assert (employee.getSalary().equals(createdEmployee.getSalary()));
		assert (employee.getHireDate().equals(now));

		employeeRepository.deleteById(createdEmployee.getId());
	}

	@Test
	public void deleteEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setCommissionPct(2.3);
		employee.setEmail("email@email.com");
		employee.setFirstName("Nombre");
		employee.setLastName("apellido");
		employee.setDepartment(departmentRepository.findById(1L).get());
		employee.setJob(jobRepository.findById(1L).get());
		employee.setPhoneNumber("1234567");
		employee.setSalary(1700.0);
		LocalDate now = LocalDate.now();
		employee.setHireDate(now);

		employee = employeeRepository.save(employee);

		this.mockMvc.perform(delete("/api/employee/" + employee.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		assert (!employeeRepository.findById(employee.getId()).isPresent());
	}

	@Test
	public void updateEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setCommissionPct(2.3);
		employee.setEmail("email@email.com");
		employee.setFirstName("Nombre");
		employee.setLastName("apellido");
		employee.setDepartment(departmentRepository.findById(1L).get());
		employee.setJob(jobRepository.findById(1L).get());
		employee.setPhoneNumber("1234567");
		employee.setSalary(1700.0);
		LocalDate now = LocalDate.now();
		employee.setHireDate(now);

		employee = employeeRepository.save(employee);

		employee.setEmail("nuevo@email.com");
		employee.setLastName("Last Name");

		MvcResult result = this.mockMvc.perform(put("/api/employee/" + employee.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee)))
				.andExpect(status().isOk()).andReturn();

		Employee createdEmployee = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);
		assert (employee.getCommissionPct().equals(createdEmployee.getCommissionPct()));
		assert (employee.getFirstName().equals(createdEmployee.getFirstName()));
		assert (employee.getLastName().equals(createdEmployee.getLastName()));
		assert (employee.getDepartment().getId().equals(createdEmployee.getDepartment().getId()));
		assert (employee.getJob().getId().equals(createdEmployee.getJob().getId()));
		assert (employee.getPhoneNumber().equals(createdEmployee.getPhoneNumber()));
		assert (employee.getSalary().equals(createdEmployee.getSalary()));
		assert (employee.getHireDate().equals(now));

		employeeRepository.deleteById(createdEmployee.getId());
	}

}
