package dao.impl;

import java.util.List;


import dao.DeptDAO;
import jakarta.persistence.*;
import model.DeptDO;
import model.EmpDO;
import utils.JPAUtil;

public class DeptDAOImpl implements DeptDAO {


    @Override
    public void insert(DeptDO deptDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(deptDO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(DeptDO deptDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        DeptDO deptDO1 = entityManager.find(DeptDO.class, deptDO.getDeptno());
        if (deptDO1 != null) {
            try {
                transaction.begin();
                deptDO1.setDeptno(deptDO.getDeptno());
                deptDO1.setDname(deptDO.getDname());
                deptDO1.setLoc(deptDO.getLoc());
                transaction.commit();
            } catch (Exception e) {
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
    public void delete(Integer deptno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            DeptDO deptDO = entityManager.find(DeptDO.class, deptno);
            entityManager.remove(deptDO);
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
    public DeptDO findByPrimaryKey(Integer deptno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.find(DeptDO.class, deptno);
    }

    @Override
    public List<DeptDO> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        //Name Query
        TypedQuery<DeptDO> query = entityManager.createNamedQuery("dept.all", DeptDO.class);
        //JPQL Query
//        Query query = entityManager.createQuery("SELECT dept FROM DeptDO dept");
        //Native Query
//        Query query = entityManager.createNativeQuery("SELECT * FROM dept2", DeptDO.class);
        return query.getResultList();
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        // FETCH: 一次查出一方及多方，而非預設的 Lazy Loading（先查一方，等到要使用到多方的屬性時，才再發送 sql 至資料庫中查詢多方）
        TypedQuery<DeptDO> query =
                entityManager.createQuery("SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno", DeptDO.class);
        query.setParameter("deptno", deptno);
        DeptDO deptDO = query.getSingleResult();
        return deptDO.getEmpDOs();
    }



}
