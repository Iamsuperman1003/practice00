package practice00.repository;

import practice00.model.entity.EmpDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository  extends JpaRepository<EmpDO, Integer> {
}
