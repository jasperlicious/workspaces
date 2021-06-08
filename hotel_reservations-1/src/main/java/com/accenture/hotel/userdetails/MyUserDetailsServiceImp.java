//package com.accenture.hotel.userdetails;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.accenture.hotel.entity.User;
//import com.accenture.hotel.repository.UserRepository;
//
//public class MyUserDetailsServiceImp implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//     
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//         
//        if (user == null) {
//            throw new UsernameNotFoundException("Could not find user");
//        }
//         
//        return new MyUserDetails(user);
//    }
//
//}
