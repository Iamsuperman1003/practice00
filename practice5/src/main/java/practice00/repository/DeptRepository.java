package practice00.repository;

import practice00.model.entity.DeptDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<DeptDO, Integer> {

    @Query(value = "SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno")
    DeptDO getOneDeptWithEmps(@Param("deptno") Integer deptno);

}
