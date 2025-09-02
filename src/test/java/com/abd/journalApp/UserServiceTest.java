/*
package com.abd.journalApp;

import com.abd.journalApp.repository.UserRepository;
import com.abd.journalApp.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testAdd(){
        assertEquals(4,2+2);
    }

    @ParameterizedTest
    @CsvSource({
            "Sak",
            "Abd",
            "Waj",
            "Kha"
    })
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name),"Failed for "+name );
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,9"
    })
    public void testParameters(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
*/
