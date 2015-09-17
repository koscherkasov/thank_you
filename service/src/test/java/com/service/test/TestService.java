package com.service.test;


import com.db.DaoUser;
import com.db.DaoUserInt;
import com.model.Department;
import com.model.Role;
import com.model.User;
import com.service.Service;
import com.service.ServiceInt;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Konstantin on 22.08.2015.
 */
public class TestService {


    @Test
    public void testCheckPassword_StatusOk() {
        ServiceInt service = new Service();
        DaoUserInt daoUser = mock(DaoUser.class);
        when(daoUser.getIdByEmailPassword("kos@mail.ru", "12345")).thenReturn(Long.valueOf(1));
        service.setDaoUser(daoUser);

        Long id = service.checkPassword("kos@mail.ru", "12345");
        assertEquals((long) id, 1);
    }

    @Test
    public void testCheckPassword_StatusError() {
        ServiceInt service = new Service();
        DaoUserInt daoUser = mock(DaoUser.class);
        when(daoUser.getIdByEmailPassword("kos@mail.ru", "12")).thenReturn(null);
        service.setDaoUser(daoUser);

        Long id = service.checkPassword("kos@mail.ru", "12");
        assertEquals(id, null);
    }

    @Test
    public void testGetUserById() {
        ServiceInt service = new Service();
        DaoUserInt daoUser = mock(DaoUser.class);
        Department localDep = new Department(Long.valueOf(1), "dev");
        Role localRole = new Role(Long.valueOf(1), "java");
        User localUser = new User(Long.valueOf(1), "1qa", "kos", "cher", "1@ya.ru", "123456789", localDep, localRole);
        when(daoUser.getUserById(Long.valueOf(1))).thenReturn(localUser);
        service.setDaoUser(daoUser);

        User user = service.getUserById(Long.valueOf(1));
        Department testDep = new Department(Long.valueOf(1), "dev");
        Role testRole = new Role(Long.valueOf(1), "java");
        User testUser = new User(Long.valueOf(1), "1qaS", "kos", "cher", "1@ya.ru", "123456789", testDep, testRole);
        assertEquals(user.getEmail(), testUser.getEmail()); //change to assertEquals(user,testUser);
    }



}
