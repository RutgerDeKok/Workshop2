package main.java.daos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import main.java.model.*;

//@Repository
public interface AdressRepository extends CrudRepository<Adress,Long> {
    
}