package Graphicc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GraphiccRepository extends JpaRepository<Graphicc, Integer> {

}
