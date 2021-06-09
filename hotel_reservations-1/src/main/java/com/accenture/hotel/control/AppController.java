package com.accenture.hotel.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.hotel.entity.Room;
import com.accenture.hotel.entity.User;
import com.accenture.hotel.repository.RoomRepository;
import com.accenture.hotel.repository.UserRepository;

@Controller
@RequestMapping("/")
public class AppController {
	
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();	
	
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private RoomRepository roomRepository;
		
		@GetMapping("/")
		public String showLogin1() {
			return "login";
		}		
		
		@GetMapping("/login")
		public String showLogin() {
			return "login";
		}
		
//		@GetMapping("/register")
//		public String showRegister() {
//			return "register";
//		}		
		
		@GetMapping("/register")
		public String showRegistrationForm(Model model) {
			model.addAttribute("user", new User());			
			return "register";
		}
		
		@PostMapping("/process_register")
		public String processRegister(User user) {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user.setAccess("USER");
			
			userRepository.save(user);
			return "login";
		}
		
		@RequestMapping("/admin/dashboard")
		public String adminDashboard() {
			return "admin/dashboard";
		}
				//FOR ADMIN ACCOUNTS
				@GetMapping("/admin/account")
				public String showAccountPage(Model model) {
					List<User> Users = userRepository.findAll();
					model.addAttribute("users", Users);
					return "admin/accounts";
				}		
				
				@GetMapping("/admin/adminAddAccount")
				public String showFormAddAccountPage(Model model) {
					User Users = new User();
					int userNum = userRepository.countStaff() + 1;
					String username = "username"+userNum;
					Users.setUsername(username);
					
					String password = "password"+userNum;
					Users.setPassword(password);
					model.addAttribute("staff", Users);
					return "admin/admin_add_account";
				}
				
				@PostMapping("/admin/saveAccount")
				public String saveAccount(@ModelAttribute("staff") User user) {
					
					String encodedPassword = passwordEncoder.encode(user.getPassword());
					user.setPassword(encodedPassword);
					user.setAccess("STAFF");
					userRepository.save(user);					
					return "redirect:/admin/account";
				}	
				
				@GetMapping("/admin/accountDelete")
				public String deleteAccount(@RequestParam("accountID") Long accountID, Model model) {
					userRepository.deleteById(accountID);
					return "redirect:/admin/account";
				}
				
				//FOR ADMIN ROOMS
				@GetMapping("/admin/room")
				public String showRoomPage(Model model) {
					List<Room> Room = roomRepository.findNoReservation();
					model.addAttribute("room", Room);
					return "admin/rooms";
				}
				
				@GetMapping("/admin/adminAddRoom")
				public String showFormAddRoomPage(Model model) {
					Room Rooms = new Room();
					int roomNum = roomRepository.countRoom();
					if (roomNum < 100) {
						roomNum +=101;
					}
					else
						roomNum += 1;
					Rooms.setNumber(roomNum);
					model.addAttribute("room", Rooms);
					return "admin/admin_add_room";
				}
				
//				@GetMapping("/admin/adminExistingRoom")
//				public String showFormExistingRoomPage(Model model) {
//					Room Rooms = new Room();
//					model.addAttribute("room", Rooms);
//					return "admin/admin_add_room_v2";
//				}	
				
				@GetMapping("/admin/updateRoom")
				public String updateRoom(@RequestParam("roomID") int roomID, Model model) {
					Room Room = roomRepository.findbyRoomId(roomID);
					model.addAttribute("room", Room);
					return "admin/admin_update_room";
				}
				
				@PostMapping("/admin/saveRoom")
				public String saveRoom(@ModelAttribute("room") Room room) {
						roomRepository.save(room);					
						return "redirect:/admin/room";
				}				
				
				@GetMapping("/admin/roomDelete")
				public String deleteRoom(@RequestParam("roomID") int roomID, Model model) {
					roomRepository.deleteById(roomID);
					return "redirect:/admin/room";
				}	
				
				@GetMapping("/admin/reserve")
				public String showReservePage(Model model) {
					List<Room> Room = roomRepository.findAllReservation();
					model.addAttribute("room", Room);
					return "admin/reserve";
				}	
				
				@GetMapping("/admin/cancelReservation")
				public String roomCancelReservebyAdmin(@RequestParam("roomID") int roomID) {
					roomRepository.updateReservation("", roomID);
					return "redirect:/admin/reserve";
				}				
							

		@RequestMapping("/staff/dashboard")
		public String staffDashboard() {
			return "staff/dashboard";
		}
		
			//FOR STAFF ROOMS		
			@GetMapping("/staff/room")
			public String showStaffRoomPage(Model model) {
				List<Room> Room = roomRepository.findNoReservation();
				model.addAttribute("room", Room);
				return "staff/rooms";
			}
			
			@GetMapping("/staff/staffAddRoom")
			public String showFormAddRoomPagebyStaff(Model model) {
				Room Rooms = new Room();
				int roomNum = roomRepository.countRoom();
				if (roomNum < 100) {
					roomNum +=101;
				}
				else
					roomNum += 1;
				Rooms.setNumber(roomNum);
				model.addAttribute("room", Rooms);
				return "staff/staff_add_room";
			}			
			
			@GetMapping("/staff/updateRoom")
			public String updateRoombyStaff(@RequestParam("roomID") int roomID, Model model) {
				Room Room = roomRepository.findbyRoomId(roomID);
				model.addAttribute("room", Room);
				return "staff/staff_update_room";
			}	
			
			@PostMapping("/staff/saveRoom")
			public String saveRoombyStaff(@ModelAttribute("room") Room room) {
					roomRepository.save(room);					
					return "redirect:/staff/room";
			}
			
			@GetMapping("/staff/roomDelete")
			public String deleteRoombyStaff(@RequestParam("roomID") int roomID, Model model) {
				roomRepository.deleteById(roomID);
				return "redirect:/staff/room";
			}				
			
			@GetMapping("/staff/reserve")
			public String showStaffReservePage(Model model) {
				List<Room> Room = roomRepository.findAllReservation();
				model.addAttribute("room", Room);
				return "staff/reserve";
			}
			
			@GetMapping("/staff/cancelReservation")
			public String roomCancelReservebyStaff(@RequestParam("roomID") int roomID) {
				roomRepository.updateReservation("", roomID);
				return "redirect:/staff/reserve";
			}								
				
		@RequestMapping("/user/dashboard")
		public String userDashboard() {
			return "user/dashboard";
		}	
	
			@GetMapping("/user/dashboard")
			public String showUserDashboardPage(Model model) {
				return "user/dashboard";
			}		
		
			@GetMapping("/user/room")
			public String showUserRoomPage(Model model) {
				List<Room> Room = roomRepository.findbyReservation("");
				model.addAttribute("room", Room);
				return "user/rooms";
			}

			@PostMapping("/user/userRoomReserveDetails")
			public String UserRoomReserve(@ModelAttribute("room") Room room) {
				room.setReserved("User");
				roomRepository.save(room);
				return "redirect:/user/reserve";
			}
			
			@GetMapping("/user/reserve")
			public String showUserShowReservePage(Model model) {
				List<Room> Room = roomRepository.findReservation("User");
				model.addAttribute("room", Room);
				return "user/reserve";
			}
			
			@GetMapping("/user/userCancelReservation")
			public String UserRoomCancelReserve(@RequestParam("roomID") int roomID) {
				roomRepository.updateReservation("", roomID);
				return "redirect:/user/reserve";
			}	
			
			//@{/user/userRoomReserve(roomID=${tempRoom.id})}
			@GetMapping("/user/userRoomReserve")
			public String userReserveDetaisl(@RequestParam("roomID") int roomID, Model model) {
				Room Room = roomRepository.findbyRoomId(roomID);
				model.addAttribute("room", Room);
				return "user/reserve_details";
			}			
			
		
	
		
			
}
