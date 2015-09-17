package com.db.test;

import com.db.DaoLikeInt;
import com.db.DaoUserInt;
import com.model.Department;
import com.model.Like;
import com.model.Role;
import com.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Konstantin on 29.08.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:db_test.xml"})
public class TestDb {

    @Autowired
    private DaoUserInt daoUserTest;

    @Autowired
    private DaoLikeInt daoLikeTest;


    @Test
    public void testChangePassword() {
        final String HASH_PASS_BEFORE = "123456";
        final String HASH_PASS_TEST = "098765";
        final Long USER_ID = Long.valueOf(1);

        User user = daoUserTest.getUserById(USER_ID);
        if (user==null) daoUserTest.setUser("1","a","b","a@a.ru","2",new Department(),new Role());
        user = daoUserTest.getUserById(USER_ID);

        //if hashPassword equals before test, change hashPassword
        if (user.getHashPass().equals(HASH_PASS_TEST)) daoUserTest.changePassword(USER_ID, HASH_PASS_BEFORE);

        //start test
        daoUserTest.changePassword(USER_ID, HASH_PASS_TEST);
        assertEquals(daoUserTest.getUserById(USER_ID).getHashPass(), HASH_PASS_TEST);

        //return HashPass for next test
        daoUserTest.changePassword(USER_ID, HASH_PASS_BEFORE);

    }

    @Test
    public void testSetLikeShowed() {
        final Long ID_SENDER = Long.valueOf(1);
        final Long ID_RECIEVER = Long.valueOf(2);
        final String COMMENT = "text";
        final int TYPE_BAGE = 5;

        User userSender = daoUserTest.getUserById(ID_SENDER);
        if (userSender==null) daoUserTest.setUser("1","a","b","a@a.ru","2",new Department(),new Role());

        User userReciever = daoUserTest.getUserById(ID_RECIEVER);
        if (userReciever==null) daoUserTest.setUser("1","a","c","b@b.ru","3",new Department(),new Role());

        //create test
        daoLikeTest.setLike(ID_SENDER, ID_RECIEVER, COMMENT, TYPE_BAGE);
        List<Like> list = daoLikeTest.getListLikeByIdReciever(ID_RECIEVER);
        Like like = list.get(list.size() - 1);

        //start test
        daoLikeTest.setLikeShowed(like.getId());
        list = daoLikeTest.getListLikeByIdReciever(ID_RECIEVER);
        like = list.get(list.size() - 1);
        assertEquals(like.getIsShow(), true);

        //delete test-Like
        daoLikeTest.delLike(like.getId());

    }

}
