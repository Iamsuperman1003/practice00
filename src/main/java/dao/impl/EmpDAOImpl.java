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
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.DeptDO;
import model.EmpDO;
import utils.JPAUtil;

public class EmpDAOImpl implements EmpDAO {

    @Override
    public void insert(EmpDO empDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(empDO);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }


    @Override
    public void update(EmpDO empDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        EmpDO empDO1 = entityManager.find(EmpDO.class, empDO.getEmpno());
        if (empDO1 != null) {
            try {
                transaction.begin();
                empDO1.setEmpno(empDO.getEmpno());
                empDO1.setEname(empDO.getEname());
                empDO1.setJob(empDO.getJob());
                empDO1.setHiredate(empDO.getHiredate());
                empDO1.setSal(empDO.getSal());
                empDO1.setComm(empDO.getComm());
                empDO1.setDeptDO(empDO.getDeptDO());
                transaction.commit();
            }catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }finally {
                if (entityManager.isOpen()) {
                    entityManager.close();
                }
            }

        }

    }

    @Override
    public void delete(Integer empno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        EmpDO empDO = entityManager.find(EmpDO.class, empno);
        try{
            transaction.begin();
            entityManager.remove(empDO);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public EmpDO findByPrimaryKey(Integer empno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.find(EmpDO.class, empno);
    }

    @Override
    public List<EmpDO> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        //Name Query
        TypedQuery<EmpDO> query = entityManager.createNamedQuery("emp.all", EmpDO.class);
        //JPQL Query
        //Query query = entityManager.createQuery("SELECT emp FROM EmpDO emp");
        //Native Query
        //Query query = entityManager.createNativeQuery("SELECT * FROM emp2", EmpDO.class);
        return query.getResultList();
    }
    


    }


