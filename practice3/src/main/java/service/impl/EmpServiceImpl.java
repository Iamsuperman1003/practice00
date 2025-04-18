package service.impl;

import java.time.LocalDate;
import java.util.List;

import dao.EmpDAO;
import dao.impl.EmpDAOImpl;
import model.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.EmpService;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {


    private EmpDAO dao;

    @Autowired
    public EmpServiceImpl(EmpDAO dao) {
        this.dao = dao;
    }


    @Override
    public EmpDO addEmp(EmpDO empDO) {
        dao.insert(empDO);
        return empDO;
    }

    @Override
    public EmpDO updateEmp(EmpDO empDO) {
        EmpDO empDO1 = dao.findByPrimaryKey(empDO.getEmpno());
        if (empDO1 != null) {
            empDO1.setEname(empDO.getEname());
            empDO1.setJob(empDO.getJob());
            empDO1.setHiredate(empDO.getHiredate());
            empDO1.setSal(empDO.getSal());
            empDO1.setComm(empDO.getComm());
            dao.update(empDO1);
        }
        return empDO1;
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
