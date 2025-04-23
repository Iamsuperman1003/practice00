package practice00.model.VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpVO {
    private Integer empno;

    private String ename;

    private String job;

    private String hiredate;

    private Double salary;

    private Double comm;

    private Integer deptno;

    private DeptVO deptVO;
}
