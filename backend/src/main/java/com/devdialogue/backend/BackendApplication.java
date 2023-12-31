package com.devdialogue.backend;

import com.devdialogue.backend.domain.Admin;
import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.services.AdminService;
import com.devdialogue.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	@Autowired
	private AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String ... args) throws Exception {
		String authorities = Utils.getAuthoritiesForUsers().get("ADMIN").concat("::ADMIN");
		Admin admin = Admin.builder()
				.name("Dev dialogue Admin")
				.email("admin@devdialogue.cc")
				.securedUser(
						SecuredUser.builder()
								.username("admin")
								.password("admin@1234")
								.authorities(authorities)
								.build()
				)
				.build();
		adminService.createAdmin(admin);
	}
}
