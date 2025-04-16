package dao;

import static org.junit.Assert.*;

import model.DeptDO;
import org.junit.Before;
import org.junit.Test;
import dao.impl.EmpDAOImpl;
import model.EmpDO;

import java.time.LocalDate;
import java.util.List;

public class EmpDAOImplTest {

    private EmpDAO dao;

    @Before
    public void setUp() {
        dao = new EmpDAOImpl();
    }

    @Test
    public void testInsert() {
        EmpDO emp = new EmpDO();
        emp.setEname("王小明1");
        emp.setJob("manager");
        emp.setHiredate(LocalDate.parse("2020-04-01"));
        emp.setSal(50000.0);
        emp.setComm(500.0);
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(10);
        emp.setDeptDO(deptDO);

        dao.insert(emp);
        assertTrue(emp.getEmpno() > 0);
    }

    @Test
    public void testUpdate() {
        EmpDO emp = new EmpDO();
        emp.setEmpno(3); // 請確認這筆員工要存在
        emp.setEname("clark");
        emp.setJob("manager");
        emp.setHiredate(LocalDate.parse("1981-01-09"));
        emp.setSal(2450.0);
        emp.setComm(0.0);
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(20);
        emp.setDeptDO(deptDO);

        dao.update(emp);
        EmpDO updated = dao.findByPrimaryKey(3);

        assertEquals("clark", updated.getEname());
        assertEquals("manager", updated.getJob());
    }

    @Test
    public void testDelete() {
        dao.delete(7014); // 假設這筆存在
        EmpDO deleted = dao.findByPrimaryKey(7014);
        assertNull(deleted);
    }

    @Test
    public void testFindByPrimaryKey() {
        EmpDO emp = dao.findByPrimaryKey(3);
        assertNotNull(emp);
        System.out.println(emp.getEmpno() + "," + emp.getEname() + "," + emp.getJob());
    }

    @Test
    public void testGetAll() {
        List<EmpDO> list = dao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);

        for (EmpDO emp : list) {
            System.out.println(emp.getEmpno() + "," + emp.getEname() + "," + emp.getJob());
        }
    }
}
