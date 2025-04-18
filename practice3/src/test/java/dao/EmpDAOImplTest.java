package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import model.EmpDO;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class EmpDAOImplTest {

    // Field Injection
    @Autowired
    private EmpDAO dao;

    @Test
    public void findByPrimaryKey() {
        EmpDO empDO = dao.findByPrimaryKey(1);
        assertEquals(Integer.valueOf(1), empDO.getEmpno());
        assertEquals("king", empDO.getEname());
        assertEquals("president", empDO.getJob());
    }

    @Test
    public void getAll() {
        List<EmpDO> list = dao.getAll();
        for (EmpDO empDO : list) {
            System.out.print(empDO.getEmpno() + ",");
            System.out.print(empDO.getEname() + ",");
            System.out.print(empDO.getJob() + ",");
            System.out.print(empDO.getHiredate() + ",");
            System.out.print(empDO.getSal() + ",");
            System.out.print(empDO.getComm() + ",");
            System.out.print(empDO.getDeptDO().getDeptno());
            System.out.println();
        }
    }

}