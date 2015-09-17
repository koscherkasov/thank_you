package com.epam.web.controller;

import com.model.Like;
import com.model.User;
import com.service.ServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    ServiceInt service;

    @RequestMapping(value = {"/", "/user*"}, method = RequestMethod.GET)
    public ModelAndView adminPage(@RequestParam(value = "time", required = false, defaultValue = "1") Integer time,
                                  HttpServletRequest request) {

        ModelAndView model = getUserModel(null, time, request);
        return model;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView userPage(@PathVariable Long id,
                                 @RequestParam(value = "time", required = false, defaultValue = "1") Integer time,
                                 HttpServletRequest request) {

        ModelAndView model = getUserModel(id, time, request);
        return model;
    }

    @RequestMapping(value = "/like", method = RequestMethod.GET)
    public ModelAndView likePage(@RequestParam(value = "idRec", required = false) Long idRec, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String myMail = request.getUserPrincipal().getName();
        User myUser = service.getUserByEmail(myMail);
        model.addObject("myUser", myUser);
        if (service.getNumLikeByIdSenderToDay(myUser.getId()) >= 3) {
            model.setViewName("moreThree");
            return model;
        }
        model.addObject("idRec", idRec);
        List<User> listUser = service.getListUsers();
        Iterator<User> iter = listUser.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(myUser.getId())) iter.remove();
        }
        model.addObject("listUser", listUser);


        model.setViewName("like");
        return model;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView usersPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String myMail = request.getUserPrincipal().getName();
        User myUser = service.getUserByEmail(myMail);
        model.addObject("myUser", myUser);

        List<User> listUser = service.getListUsers();
        model.addObject("listUser", listUser);

        model.setViewName("users");
        return model;
    }

    @RequestMapping(value = "/sendlike", method = RequestMethod.GET)
    public ModelAndView sendLike(@RequestParam(value = "idRec", required = true) Long idRec,
                                 @RequestParam(value = "comment", required = true) String comment,
                                 @RequestParam(value = "typeBage", required = true) int typeBage,
                                 HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String myMail = request.getUserPrincipal().getName();
        User myUser = service.getUserByEmail(myMail);
        model.addObject("myUser", myUser);


        User someUser = service.getUserById(idRec);
        model.addObject("someUser", someUser);

        service.setLike(myUser.getId(), someUser.getId(), comment, typeBage);

        List<Like> listLike = service.getListLikeByIdReciever(someUser.getId());
        model.addObject("listLike", listLike);

        boolean showNotif = false;
        model.addObject("showNotif", showNotif);

        model.setViewName("user");

        return model;

    }

    @RequestMapping(value = "/delLike", method = RequestMethod.GET)
    public ModelAndView delLike(@RequestParam(value = "idLike", required = false) Long idLike,
                                HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String myMail = request.getUserPrincipal().getName();
        User myUser = service.getUserByEmail(myMail);
        model.addObject("myUser", myUser);

        Like like = service.getLikeById(idLike);
        Long idRec = like.getReciever().getId();
        if (like.getSender().getId().equals(myUser.getId())) service.delLike(idLike);

        return userPage(idRec, 1, request);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    //user page
    private ModelAndView getUserModel(Long id, Integer time, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.addObject("time", time);

        String myMail = request.getUserPrincipal().getName();
        User myUser = service.getUserByEmail(myMail);
        model.addObject("myUser", myUser);

        User someUser;
        if (id == null || id.equals(myUser.getId())) someUser = myUser;
        else someUser = service.getUserById(id);
        model.addObject("someUser", someUser);

        List<Like> listLike = null;
        if (time.equals(1)) listLike = service.getListLikeByIdReciever(someUser.getId());
        else listLike = service.getListLikeByIdReciever(someUser.getId(), time);
//        Collections.reverse(listLike);
        model.addObject("listLike", listLike);

        Iterator<Like> iter = listLike.iterator();
        Like like = null;
        boolean showNotif = false;
        if (id == null || id.equals(myUser.getId())) {
            while (iter.hasNext()) {
                like = iter.next();
                if (!like.getIsShow()) {
                    showNotif = true;
                    service.setLikeShowed(like.getId());
                }
            }
        }
        model.addObject("showNotif", showNotif);

        model.setViewName("user");
        return model;
    }
}

