package service;

import java.time.LocalDate;
import java.util.List;

import model.EmpDO;

public interface EmpService {

    EmpDO addEmp(EmpDO empDO);

    EmpDO updateEmp(EmpDO empDO);

    void deleteEmp(Integer empno);

    EmpDO getOneEmp(Integer empno);

    List<EmpDO> getAll();

}
