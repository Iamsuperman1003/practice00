package service;

import java.util.List;

import model.DeptDO;
import model.EmpDO;

public interface DeptService {

    List<DeptDO> getAll();

    DeptDO getOneDept(Integer deptno);

    DeptDO update(Integer deptno, String dname, String loc);

    List<EmpDO> getEmpsByDeptno(Integer deptno);

    void deleteDept(Integer deptno);

}
