/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.daos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import main.java.model.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nadkine
 */
//@Repository
//@Component
public interface ProductRepository extends CrudRepository<Product,Long> {
    
}
