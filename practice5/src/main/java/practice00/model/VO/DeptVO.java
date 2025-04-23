package practice00.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeptVO {
    private Integer deptno;
    private String dname;
    private String loc;
    private List<EmpVO> empVOs;
}
