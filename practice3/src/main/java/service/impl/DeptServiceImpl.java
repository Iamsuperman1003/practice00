package service.impl;

import java.util.List;

import dao.DeptDAO;

import model.DeptDO;
import model.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {


    private DeptDAO dao;

    @Autowired
    public DeptServiceImpl(DeptDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<DeptDO> getAll() {
        return dao.getAll();
    }

    @Override
    public  DeptDO getOneDept(Integer deptno){
        return dao.findByPrimaryKey(deptno);
    }

    @Override
    @Transactional
    public DeptDO update(DeptDO deptDO) {
        DeptDO daoDo = dao.findByPrimaryKey(deptDO.getDeptno());
        if (daoDo != null) {
            daoDo.setDname(deptDO.getDname());
            daoDo.setLoc(deptDO.getLoc());
            dao.update(daoDo);
        }
        return daoDo;
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        return dao.getEmpsByDeptno(deptno);
    }

    @Override
    @Transactional
    public void deleteDept(Integer deptno) {
        dao.delete(deptno);
    }
}
