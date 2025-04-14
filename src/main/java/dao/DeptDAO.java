package dao;

import java.util.List;

import model.DeptDO;
import model.EmpDO;

public interface DeptDAO {

    void insert(DeptDO deptDO);

    void update(DeptDO deptDO);

    void delete(Integer deptno);

    DeptDO findByPrimaryKey(Integer deptno);

    List<DeptDO> getAll();

    List<EmpDO> getEmpsByDeptno(Integer deptno);

}
