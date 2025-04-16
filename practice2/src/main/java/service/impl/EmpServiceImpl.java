package service.impl;

import java.time.LocalDate;
import java.util.List;

import dao.EmpDAO;
import dao.impl.EmpDAOImpl;
import model.EmpDO;
import service.EmpService;

public class EmpServiceImpl implements EmpService {

    private EmpDAO dao;

    public EmpServiceImpl() {
        dao = new EmpDAOImpl();
    }

    @Override
    public EmpDO addEmp(String ename, String job, LocalDate hiredate, Double sal, Double comm, Integer deptno) {
        return null;
    }

    @Override
    public EmpDO updateEmp(Integer empno, String ename, String job, LocalDate hiredate, Double sal, Double comm, Integer deptno) {
        return null;
    }

    @Override
    public void deleteEmp(Integer empno) {
        dao.delete(empno);
    }

    @Override
    public EmpDO getOneEmp(Integer empno) {
        return dao.findByPrimaryKey(empno);
    }

    @Override
    public List<EmpDO> getAll() {
        return dao.getAll();
    }

}
