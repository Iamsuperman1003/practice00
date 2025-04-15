package service;

import java.time.LocalDate;
import java.util.List;

import model.EmpDO;

public interface EmpService {

    EmpDO addEmp(String ename, String job, LocalDate hiredate,
                 Double sal, Double comm, Integer deptno);

    EmpDO updateEmp(Integer empno, String ename, String job,
                    LocalDate hiredate, Double sal, Double comm, Integer deptno);

    void deleteEmp(Integer empno);

    EmpDO getOneEmp(Integer empno);

    List<EmpDO> getAll();

}
