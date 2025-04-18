package dao.impl;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


import dao.DeptDAO;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.DeptDO;
import model.EmpDO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import utils.JPAUtil;

@Repository
@Transactional
public class DeptDAOImpl implements DeptDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(DeptDO deptDO) {
        entityManager.persist(deptDO);
    }

    @Override
    public void update(DeptDO deptDO) {
        DeptDO deptDO1 = entityManager.find(DeptDO.class, deptDO.getDeptno());
        if (deptDO1 != null) {
            deptDO1.setDeptno(deptDO.getDeptno());
            deptDO1.setDname(deptDO.getDname());
            deptDO1.setLoc(deptDO.getLoc());
        }

    }

    @Override
    public void delete(Integer deptno) {
        DeptDO deptDO = entityManager.find(DeptDO.class, deptno);
        entityManager.remove(deptDO);
    }

    @Override
    public DeptDO findByPrimaryKey(Integer deptno) {
      return entityManager.find(DeptDO.class, deptno);
    }

    @Override
    public List<DeptDO> getAll() {
        Query query = entityManager.createNamedQuery("dept.all");
        return query.getResultList();
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        DeptDO deptDO = entityManager.createQuery("SELECT d FROM DeptDO d LEFT JOIN FETCH d.empDOs WHERE d.deptno = :deptno", DeptDO.class)
                .setParameter("deptno", deptno).getSingleResult();
        return deptDO.getEmpDOs();
    }


    @Override
    public List<DeptDO> findByCriteria(DeptDO deptDO) {
        //建立Criteria 工具
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptDO> cq = cb.createQuery(DeptDO.class);
        Root<DeptDO> root = cq.from(DeptDO.class);

        //建立條件清單
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (deptDO.getDeptno() != null) {
            predicates.add(cb.equal(root.get("deptno"), deptDO.getDeptno()));
        }

        if (deptDO.getDname() != null) {
            predicates.add(cb.like(root.get("dname"), "%" + deptDO.getDname() + "%"));
        }

        if (deptDO.getLoc() != null) {
            predicates.add(cb.like(root.get("loc"), "%" + deptDO.getLoc() + "%"));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(cq).getResultList();

    }


}
