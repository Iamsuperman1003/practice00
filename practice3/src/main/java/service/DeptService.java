package service;

import java.util.List;

import model.DeptDO;
import model.EmpDO;

public interface DeptService {

    List<DeptDO> getAll();//查詢所有部門

    DeptDO getOneDept(Integer deptno);//查詢單一部門（依編號）

    DeptDO update(DeptDO deptDO);//修改部門資料（依編號，傳入欄位值）

    List<EmpDO> getEmpsByDeptno(Integer deptno);//查詢部門下的員工清單

    void deleteDept(Integer deptno);//刪除部門

}
