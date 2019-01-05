package com.panjura.mosque.miyabarimosque;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panjura.mosque.miyabarimosque.bean.Role;
import com.panjura.mosque.miyabarimosque.bean.User;
import com.panjura.mosque.miyabarimosque.repository.RoleRepo;
import com.panjura.mosque.miyabarimosque.repository.UserRepository;
import com.panjura.mosque.miyabarimosque.service.GenericsDAOService;

@Controller
public class Test {

	@Autowired UserRepository us;
	@Autowired RoleRepo ur;

	@Autowired @Qualifier("userService") GenericsDAOService<User> userService;
	
	
	@GetMapping("/log")
	public String hello12() {
		return "views/login";
	}

	@GetMapping("/html")
	public String hello1() {
		
	//	Role r =ur.findByRoleId(1l);
		Role r =ur.findByRoleName("admin");
		System.out.println("==="+ r.getRoleName());
	
		return "views/test";
	}
	
	@GetMapping("/getall")
	@ResponseBody
	public List<User> testGetAllUser() {
		return userService.getAllData();
	}
	@GetMapping("/getone/{userid}")
	@ResponseBody
	public Optional<User> testGetOneUser(@PathVariable Integer userid) {
		return userService.findById(userid);
	}
	
	@DeleteMapping("/delete/{userid}")
	@ResponseBody
	public void testDeleteOneUser(@PathVariable Integer userid) {
		Optional<User> user = userService.findById(userid);
		if (StringUtils.isEmpty(user)) {
			System.out.println("==========");
		}
			userService.deleteData(userid);
	}
	
	@PostMapping("/saveUser")
	@ResponseBody
	public void saveOrUpdateUser(@RequestBody User user) {
		System.out.println("========================");
		userService.saveOrUpdateData(user);
	}
	
	@GetMapping("/user1")
	@ResponseBody
	public List<User> helloUser() {
		Role r =ur.findByRoleId(4);
		Role r2 =ur.findByRoleId(2);
		System.out.println("=========== "+r.getRoleName());
		Role rr = new Role();
		Role rr1 = new Role();
		rr.setRoleId(r.getRoleId());
		rr.setRoleName(r.getRoleName());
		rr1.setRoleId(r2.getRoleId());
		rr1.setRoleName("admin2");
		
		
		
		User u = new User();
		u.setUserId(1);
		u.setFirstName("akramul");
		u.setLastName("hasan");
		u.setPassword("1234");
		//u.addRole(rr);
		u.setRoles(Arrays.asList(rr, rr1));
		us.save(u);
		return us.findAll();
	}
	
	
//	@Scheduled(fixedDelay = 5000)
	public void testScheduling() {
		System.out.println("Fixed delay task = " + System.currentTimeMillis() / 1000);
	}
}
