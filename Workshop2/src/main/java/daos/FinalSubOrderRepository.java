
package main.java.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import main.java.model.*;

//@Repository
public interface FinalSubOrderRepository extends CrudRepository<FinalSubOrder,Long> {
    
}
