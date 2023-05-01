package org.example;

import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Main(UserRepository userRepository,
                RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        Role adminRole = new Role(RoleType.ADMIN);
        Role normalRole = new Role(RoleType.NORMAL);

        Role adminRoleSaved = roleRepository.save(adminRole);
        Role normalRoleSave = roleRepository.save(normalRole);


        User murat = new User();
        murat.setFirstName("Murat");
        murat.setLastName("YAPICI");
        murat.setUsername("yapicimurat");
        murat.setPassword("kalem123");
        murat.getRoles().add(adminRole);

        User burak = new User();
        burak.setFirstName("Burak");
        burak.setLastName("YAPICI");
        burak.setUsername("yapiciburak");
        burak.setPassword("kalem123");
        burak.getRoles().add(normalRole);

        userRepository.save(murat);
        userRepository.save(burak);
        */

    }
}