package dao;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import dao.DeptDAO;
import dao.impl.DeptDAOImpl;
import model.DeptDO;

import java.util.List;

public class DeptDAOImplTest {

    private DeptDAO dao;

    @Before
    public void setUp() {
        dao = new DeptDAOImpl();
    }

    @Test
    public void testInsert() {
        DeptDO dept = new DeptDO();
        dept.setDname("製造部");
        dept.setLoc("美國洛杉磯");

        dao.insert(dept);
        assertNotNull(dept.getDeptno());          // 防止 NPE
        assertTrue(dept.getDeptno() > 0);         // 自動產生的主鍵驗證
    }

    @Test
    public void testUpdate() {
        DeptDO dept = new DeptDO();
        dept.setDeptno(10); // 請確保此部門存在！
        dept.setDname("財務部2");
        dept.setLoc("臺灣台北2");

        dao.update(dept);
        DeptDO updated = dao.findByPrimaryKey(10);
        assertEquals("財務部2", updated.getDname());
    }

    @Test
    public void testDelete() {
        dao.delete(30); // 這筆資料需要原本存在，不然不會測出錯
        DeptDO deleted = dao.findByPrimaryKey(30);
        assertNull(deleted);
    }

    @Test
    public void testFindByPrimaryKey() {
        DeptDO dept = dao.findByPrimaryKey(20); // 請確保這筆存在
        assertNotNull(dept);
        System.out.println(dept.getDeptno() + "," + dept.getDname() + "," + dept.getLoc());
    }

    @Test
    public void testGetAll() {
        List<DeptDO> list = dao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        for (DeptDO dept : list) {
            System.out.println(dept.getDeptno() + "," + dept.getDname() + "," + dept.getLoc());
        }
    }

    @Test
    public void testGetEmpsByDeptno() {
        List<?> emps = dao.getEmpsByDeptno(10);
        assertNotNull(emps);
        for (Object emp : emps) {
            System.out.println(emp);
        }
    }
}
