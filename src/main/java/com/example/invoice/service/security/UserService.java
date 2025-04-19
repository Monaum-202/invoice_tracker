package com.example.invoice.service.security;



import com.example.invoice.dto.security.UserDTO;
import com.example.invoice.entity.security.Role;
import com.example.invoice.entity.security.Users;
import com.example.invoice.enums.ERole;
import com.example.invoice.repository.security.RoleRepository;
import com.example.invoice.repository.security.UserRepository;
import com.example.invoice.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PasswordEncoder encoder;

    public UserService(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }



    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName(ERole.ROLE_ADMIN.toString());
        adminRole.setRoleDescription("Admin role");
        adminRole.setDateCreated(OffsetDateTime.now());
        adminRole.setLastUpdated(OffsetDateTime.now());
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(ERole.ROLE_USER.toString());
        userRole.setRoleDescription("Default role for newly created record");
        userRole.setDateCreated(OffsetDateTime.now());
        userRole.setLastUpdated(OffsetDateTime.now());
        roleRepository.save(userRole);

        Role roleMODERATOR = new Role();
        roleMODERATOR.setRoleName("ROLE_MODERATOR");
        roleMODERATOR.setRoleDescription("Default role for newly ROLE_MODERATOR record");
        roleMODERATOR.setDateCreated(OffsetDateTime.now());
        roleMODERATOR.setLastUpdated(OffsetDateTime.now());
        roleRepository.save(roleMODERATOR);


//        Role roleLAB_ENTRY = new Role();
//        roleLAB_ENTRY.setRoleName("ROLE_LAB_ENTRY");
//        roleLAB_ENTRY.setRoleDescription("Default role for newly ROLE_LAB_ENTRY record");
//        roleLAB_ENTRY.setDateCreated(OffsetDateTime.now());
//        roleLAB_ENTRY.setLastUpdated(OffsetDateTime.now());
//        roleRepository.save(roleLAB_ENTRY);


        Users adminUsers = new Users();
        adminUsers.setUserName("admin123");
        adminUsers.setPassword(getEncodedPassword("admin@pass"));
        adminUsers.setUserFirstName("admin");
        adminUsers.setUserLastName("admin");
        adminUsers.setEmail("admin@gmail.com");
        adminUsers.setDateCreated(OffsetDateTime.now());
        adminUsers.setLastUpdated(OffsetDateTime.now());

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(roleMODERATOR);

        adminUsers.setRoles(adminRoles);
        userRepository.save(adminUsers);

    }


    public List<UserDTO> findAll() {
        final List<Users> users = userRepository.findAll(Sort.by("userName"));
        return users.stream()
                .map((user01) -> mapToDTO(user01, new UserDTO()))
                .toList();
    }

    public UserDTO get(final String userName) {
        return userRepository.findById(userName)
                .map(user01 -> mapToDTO(user01, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final UserDTO userDTO) {
        final Users users = new Users();
        mapToEntity(userDTO, users);
        users.setUserName(userDTO.getUserName());
        return userRepository.save(users).getUserName();
    }

    public void update(final String userName, final UserDTO userDTO) {
        final Users users = userRepository.findById(userName)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, users);
        userRepository.save(users);
    }

    public void delete(final String userName) {
        userRepository.deleteById(userName);
    }

    private UserDTO mapToDTO(final Users users, final UserDTO userDTO) {
        userDTO.setUserName(users.getUserName());
        userDTO.setUserFirstName(users.getUserFirstName());
        userDTO.setUserLastName(users.getUserLastName());
        userDTO.setPassword(users.getPassword());
        userDTO.setEmail(users.getEmail());
        userDTO.setEnabled(users.getEnabled());
        userDTO.setCredentialsNonExpired(users.getCredentialsNonExpired());
        userDTO.setAccountNonExpired(users.getAccountNonExpired());
        userDTO.setAccountNonLocked(users.getAccountNonLocked());
        userDTO.setRoles(users.getRoles() == null ? null : users.getRoles().stream()
                .map(role01 -> role01.getRoleName())
                .toList());
        return userDTO;
    }

    private Users mapToEntity(final UserDTO userDTO, final Users users) {
        users.setUserFirstName(userDTO.getUserFirstName());
        users.setUserLastName(userDTO.getUserLastName());
        users.setPassword(encoder.encode(userDTO.getPassword()));
        users.setEmail(userDTO.getEmail());
        users.setEnabled(userDTO.getEnabled());
        users.setCredentialsNonExpired(userDTO.getCredentialsNonExpired());
        users.setAccountNonExpired(userDTO.getAccountNonExpired());
        users.setAccountNonLocked(userDTO.getAccountNonLocked());
        final List<Role> roles = roleRepository.findAllById(
                userDTO.getRoles() == null ? Collections.emptyList() : userDTO.getRoles());
        if (roles.size() != (userDTO.getRoles() == null ? 0 : userDTO.getRoles().size())) {
            throw new NotFoundException("one of roles not found");
        }
        users.setRoles(roles.stream().collect(Collectors.toSet()));
        return users;
    }

    public boolean userNameExists(final String userName) {
        return userRepository.existsByUserNameIgnoreCase(userName);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }



    public Page<UserDTO> getAllUsers(int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(Sort.Order.asc(sortBy)); // Default is ascending order
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserDTO> userDTOPage = userRepository.findAll(pageable).map(user -> mapToDTO(user, new UserDTO()));
        return userDTOPage;
    }


    public Page<UserDTO> searchUsers(String searchTerm, int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(Sort.Order.asc(sortBy)); // Default to ascending order
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Users> userPage = userRepository.findByUserNameContainingIgnoreCaseOrUserFirstNameContainingIgnoreCaseOrUserLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                searchTerm, searchTerm, searchTerm, searchTerm, pageable);

        return userPage.map(user -> mapToDTO(user, new UserDTO()));
    }


    public List<Object[]> getModerators() {
        return userRepository.findUsersByRole("ROLE_MODERATOR");
    }
}
