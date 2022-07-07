package org.example;

import org.example.config.MyConfig;
import org.example.entity.User;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.List;

/**
 * Hello world!
 *
 */
public class  App
{
    public static void main( String[] args )
    {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        List<User> users = communication.showAllUsers();
        System.out.println(users);

        User user = new User(3L, "James", "Brown", (byte) 40);
        communication.saveUser(user);

        User updUser = new User(3L, "Thomas", "Shelby", (byte) 30);
        communication.updateUser(updUser);

        communication.delete(updUser,3L);


    }


}
