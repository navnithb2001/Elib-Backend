package com.lms.demo.controller;

import com.lms.demo.Security.Security;
import com.lms.demo.data.model.Book;
import com.lms.demo.data.model.Order;
import com.lms.demo.data.model.Roles;
import com.lms.demo.data.model.User;
import com.lms.demo.data.repository.BookRepository;
import com.lms.demo.data.repository.OrderRepository;
import com.lms.demo.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Security security;

    @GetMapping(value = "/{userid}")
    public Optional<User> getUserByid(@PathVariable Long userid) {
        return userRepository.findById(userid);
    }
    @GetMapping(value = "/{userid}/getBooks")
    public List<Book> getBooks(@PathVariable Long userid) {
        List<Book> li = new ArrayList<Book>();
        bookRepository.findAll().forEach(li::add);
        return li;
    }

    @PutMapping(value = "/{userid}/update")
    public void updateUser(@RequestBody User user, @PathVariable Long userid) {
        // security.authorizePath(userid, Roles.CLIENT);
        userRepository.save(user);
    }

    @GetMapping(value = "/{userid}/getBookingDetails")
    public List<Order> getBookingDetails(@PathVariable Long userid) throws IllegalAccessException {
        // security.authorizePath(userid, Roles.ADMIN);
        List<Order> li = new ArrayList<Order>();
        orderRepository.findAll().forEach(li::add);
        return li;
    }

    @PutMapping(value = "updateBook")
    public void updateBooks(@RequestBody Book book) {
        bookRepository.save(book);
    }
    @PostMapping(value = "/{userid}/addBook")
    public void addBooks(@RequestBody Book book,@PathVariable Long userid) throws IllegalAccessException {
        // security.authorizePath(userid, Roles.ADMIN);
        System.out.println(book);
        book.setRating((long) 0);
        //book.setISBN(book.getISBN());
            
        bookRepository.save(book);
    }

    @PostMapping(value = "/{userid}/delBook")
    public void delBooks(@RequestBody Book book,@PathVariable Long userid) throws IllegalAccessException {
        // security.authorizePath(userid, Roles.ADMIN);
        System.out.println(book);
        for(Order i: book.getOrders()){
            cancelBooking(i.getBooking_id(), userid);
        }
        bookRepository.delete(book);
    }

    @PostMapping(value = "/{userid}/makeBooking")
    public void makeBooking(@PathVariable Long userid,@RequestBody Book book) throws IllegalAccessException {
        // security.authorizePath(userid, Roles.CLIENT);

        Optional<User> optuser = userRepository.findById(userid);
        User user = optuser.get();

        int return_days = 15;
        if(user.getRole() == Roles.PRIVILEGED_CLIENT){
            return_days = 30;
        }
        Order orderDetails = new Order(Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusDays(return_days)));
        orderDetails.setBook(book);
        orderDetails.setUser(user);
        System.out.println(orderDetails.getBook().getISBN());
        orderRepository.save(orderDetails);
        book.setAvailable(book.getAvailable()-1);

        bookRepository.save(book);


    }

    @DeleteMapping(value = "/{userid}/cancelBooking/{orderDetails}")
    public void cancelBooking(@PathVariable String orderDetails,@PathVariable Long userid) throws IllegalAccessException {
        // security.authorizePath(userid, Roles.CLIENT);

        System.out.println(orderDetails.split(":")[0]);
        Optional<Order> opt_order = orderRepository.findById(orderDetails);
        Order order = opt_order.get();
        orderRepository.deleteByOrderId(orderDetails);
        String ISBN = order.getBook().getISBN();

        Optional<Book> optbook = bookRepository.findById(ISBN);

        Book book = optbook.get();
        book.setAvailable(book.getAvailable()+1);

        bookRepository.save(book);

    }

    @DeleteMapping("/{userid}/delUser")
    public void deleteUser(@RequestBody List<User> users,@PathVariable Long userid) throws Exception {
        // security.authorizePath(userid, Roles.ADMIN);
        
        for(User i: users){
            for(Order j: i.getOrder()){
                cancelBooking(j.getBooking_id(), userid);
            }
        }

        userRepository.deleteAll(users);
    }

    @GetMapping(value = "/{userid}/orders")
    public List<Order> getOrdersByUser(@PathVariable Long userid) throws IllegalAccessException {
        //security.authorizePath(userid, Roles.CLIENT);
        
        Optional<User> user = userRepository.findById(userid);

        return user.get().getOrder();

    }

}
