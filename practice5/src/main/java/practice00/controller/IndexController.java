package practice00.controller;

import practice00.model.VO.DeptVO;
import practice00.model.VO.EmpVO;
import practice00.model.entity.DeptDO;
import practice00.model.entity.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import practice00.service.DeptService;
import practice00.service.EmpService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmpService empService;

    @GetMapping({"/", "/index"})
    public String showIndex(Model model) {
        List<DeptDO> deptDOs = deptService.getAll();
        List<EmpDO> empDOs = empService.getAll();


        List<DeptVO> deptVOS = transformDeptVOs(deptDOs);
        List<EmpVO> empVOs = transformEmpVOs(empDOs);

        model.addAttribute("deptVOS", deptVOS);
        model.addAttribute("empVOs", empVOs);

        return "index";
    }


    private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOs) {
        return deptDOs
                .stream()
                .map(deptDO -> {
                    DeptVO deptVO = new DeptVO();
                    deptVO.setDeptno(deptDO.getDeptno());
                    deptVO.setDname(deptDO.getDname());
                    deptVO.setLoc(deptDO.getLoc());
                    return deptVO;
                })
                .collect(Collectors.toList());
    }

    private List<EmpVO> transformEmpVOs(List<EmpDO> empDOs) {
        return empDOs
                .stream()
                .map(empDO -> {
                    EmpVO empVO = new EmpVO();
                    empVO.setEmpno(empDO.getEmpno());
                    empVO.setEname(empDO.getEname());
                    empVO.setJob(empDO.getJob());
                    empVO.setHiredate(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                    .format(empDO.getHiredate())
                    );
                    empVO.setSalary(empDO.getSal());
                    empVO.setComm(empDO.getComm());
                    return empVO;
                })
                .collect(Collectors.toList());
    }
}
