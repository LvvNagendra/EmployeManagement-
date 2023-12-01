package com.example.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDto;
import com.example.dto.ErrorResponse;
import com.example.filegenaretor.ExcelGenerator;
import com.example.filegenaretor.PdfGenerator;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.example.util.CommonValidation;
import com.example.util.ResponseCodes;
//import com.javatechie.jwt.api.entity.AuthRequest;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class EmployeeController {
//	  @Autowired
//	    private JwtUtil jwtUtil;
//	    @Autowired
//	    private AuthenticationManager authenticationManager;
	@Autowired
	EmployeeService service;

	@GetMapping("/test")
	public String test() {
		return "String";
	}

	@PostMapping("/reg")
	public ErrorResponse registerEmployee(@RequestBody EmployeeDto request) {
		ErrorResponse response = null;
		response = new ErrorResponse();
		CommonValidation validate = new CommonValidation();
		if (validate.validateStringValues(request.getEmployeeName())) {
			response.setCode(ResponseCodes.ER102.getErrorCode());
			response.setMessage(ResponseCodes.ER102.getMessage());
			return response;
		}
		if (!validate.validateNumber(request.getPhoneNumber())) {
			response.setCode(ResponseCodes.ER105.getErrorCode());
			response.setMessage(ResponseCodes.ER105.getMessage());
			return response;
		}
		if (!validate.validateEmail(request.getEmailId())) {
			response.setCode(ResponseCodes.ER104.getErrorCode());
			response.setMessage(ResponseCodes.ER104.getMessage());
			return response;
		}
		if (validate.validateStringValues(request.getOrginizationName())) {
			response.setCode(ResponseCodes.ER103.getErrorCode());
			response.setMessage(ResponseCodes.ER103.getMessage());
			return response;
		}
		try {

			response = service.registerEmployee(request);

		} catch (Exception e) {
			response.setCode(ResponseCodes.ER105.getErrorCode());
			response.setMessage(ResponseCodes.ER105.getMessage());

		}
		return response;
	}

	@GetMapping(value = "/formDetaToExcel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> excelEmployesReport() throws IOException {
		List<EmployeeDto> model = service.getAllEmployee();
		ByteArrayInputStream in = ExcelGenerator.shipToExcel(model);

		byte[] data = IOUtils.toByteArray(in);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(ContentDisposition.attachment().filename("Employess.xlsx").build());

		return new ResponseEntity<>(data, headers, HttpStatus.OK);
	}

	@GetMapping("/export-to-pdf")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		/* List < FormModel > list = service.getAllStudentDetailss(); */
		List<EmployeeDto> list = service.getAllEmployee();
		PdfGenerator generator = new PdfGenerator();
		generator.generate(list, response);
	}

	@GetMapping("/pdfGetById/{employeeId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public void generatesPdfFile(HttpServletResponse response, @PathVariable("employeeId") long employeeId)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		List<EmployeeDto> list = service.getByemployeeId(employeeId);
		PdfGenerator generator = new PdfGenerator();
		generator.generate(list, response);
	}

	@GetMapping("/employee/{pageNo}/{pageSize}")
	public List<Employee> getPaginatedCountries(@PathVariable int pageNo, @PathVariable int pageSize) {

		return service.findPaginated(pageNo, pageSize);
	}

	@GetMapping("/pdfEmployee/{pageNo}/{pageSize}")
	public void generatesPdfFiles(HttpServletResponse response, @PathVariable int pageNo, @PathVariable int pageSize)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		List<Employee> list = service.findPaginated(pageNo, pageSize);
		PdfGenerator generator = new PdfGenerator();
		generator.generates(list, response);
	}
	@GetMapping("/empnames")
	public ResponseEntity<Object>   getAllEmployeeName() {
		ResponseEntity<Object> list= service.getAllEmployees();
		return list;
	}
//	 @PostMapping("/authenticate")
//	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//	        try {
//	            authenticationManager.authenticate(
//	                    new UsernamePasswordAuthenticationToken(authRequest.getEmployeeName(), authRequest.getPassword())
//	            );
//	        } catch (Exception ex) {
//	            throw new Exception("inavalid username/password");
//	        }
//	        return jwtUtil.generateToken(authRequest.getEmployeeName());
//	    }

}
