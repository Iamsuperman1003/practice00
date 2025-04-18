package service;

import model.DeptDO;
import model.EmpDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class EmpServicelmplTest {

    @Autowired
    private EmpService service;

    @Test
    public void addEmp() {
        EmpDO empDO = new EmpDO();
        empDO.setEname("賈斯汀");
        empDO.setJob("manager");
        empDO.setHiredate(LocalDate.parse("2025-04-18"));
        empDO.setSal(Double.valueOf(2220000));
        empDO.setComm(Double.valueOf(200));
        DeptDO deptDO = new DeptDO();
       deptDO.setDeptno(40);
       empDO.setDeptDO(deptDO);
        service.addEmp(empDO);
        assertTrue(true);
    }

    @Test
    public void updateEmp() {
        EmpDO empDO = new EmpDO();
        empDO.setEmpno(7002);
        empDO.setEname("賈斯汀");
        empDO.setJob("manager");
        empDO.setHiredate(LocalDate.parse("2025-04-18"));
        empDO.setSal(Double.valueOf(2220000));
        empDO.setComm(Double.valueOf(200));
        DeptDO deptDO = new DeptDO();
       deptDO.setDeptno(20);
       empDO.setDeptDO(deptDO);
        service.updateEmp(empDO);
        assertTrue(true);
    }


    @Test
    public void deleteEmp() {
        service.deleteEmp(23);
        assertTrue(true);
    }

    @Test
    public void getAll() {
        List<EmpDO> list = service.getAll();
        for (EmpDO empDO : list) {
            System.out.print(empDO.getEmpno() + ",");
            System.out.print(empDO.getEname() + ",");
            System.out.print(empDO.getJob() + ",");
            System.out.print(empDO.getHiredate() + ",");
            System.out.print(empDO.getSal() + ",");
            System.out.print(empDO.getComm());
            System.out.println();
        }
    }
}
