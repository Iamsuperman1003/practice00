package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import dao.EmpDAO;
import jakarta.persistence.*;
import model.DeptDO;
import model.EmpDO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import utils.JPAUtil;


@Repository
@Transactional
public class EmpDAOImpl implements EmpDAO {

    @PersistenceContext
    protected  EntityManager entityManager;

    @Override
    public void insert(EmpDO empDO) {
        entityManager.persist(empDO);
    }


    @Override
    public void update(EmpDO empDO) {
        EmpDO empDO1 = entityManager.find(EmpDO.class, empDO.getEmpno());
        if (empDO1 != null) {
            empDO1.setEmpno(empDO.getEmpno());
            empDO1.setEname(empDO.getEname());
            empDO1.setJob(empDO.getJob());
            empDO1.setHiredate(empDO.getHiredate());
            empDO1.setSal(empDO.getSal());
            empDO1.setComm(empDO.getComm());
            empDO1.setDeptDO(empDO.getDeptDO());
        }


    }

    @Override
    public void delete(Integer empno) {
        EmpDO empDO = entityManager.find(EmpDO.class, empno);
        if (empDO != null) {
            entityManager.remove(empDO);
        }
    }

    @Override
    public EmpDO findByPrimaryKey(Integer empno) {
    return entityManager.find(EmpDO.class, empno);
    }

    @Override
    public List<EmpDO> getAll() {
        Query query = entityManager.createNamedQuery("emp.all");
        return query.getResultList();
    }
    


    }


