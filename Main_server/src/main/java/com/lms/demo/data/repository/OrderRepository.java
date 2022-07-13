package com.lms.demo.data.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lms.demo.data.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

    @Modifying
    @Transactional
    @Query(value = "delete from Order c where c.booking_id = ?1")
    void deleteByOrderId(String orderId);
}
