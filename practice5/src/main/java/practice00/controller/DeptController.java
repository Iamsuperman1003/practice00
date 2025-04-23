package practice00.controller;

import practice00.model.VO.DeptVO;
import practice00.model.VO.EmpVO;
import practice00.model.entity.DeptDO;
import practice00.model.entity.EmpDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice00.service.DeptService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查詢所有部門
    @GetMapping("/listAll")
    public String listAll(Model model) {
        List<DeptDO> deptList = deptService.getAll();
        List<DeptVO> deptVOS = transformDeptVOs(deptList);
        model.addAttribute("deptVOS", deptVOS);
        return "/dept/listAll";
    }

    //查詢部門下的員工清單
    @PostMapping("/listEmps_ByDeptno")
    public String listEmpsByDeptno(Integer deptno, Model model) {
        List<EmpDO> emps = deptService.getEmpsByDeptno(deptno);
        List<EmpVO> empVOs = transformEmpVOs(emps);
        model.addAttribute("empVOs", empVOs);
        return "/dept/listEmpsByDeptno";
    }





    //查詢單一部門（依編號）
    @PostMapping ("/getOne_For_Update")
    public String getOneForUpdate(Integer deptno, Model model) {
        Optional<DeptDO> deptDO = deptService.getOneDept(deptno);
        if (deptDO.isPresent()) {
            DeptVO deptVO = transformDeptVO(deptDO.get());
            model.addAttribute("deptVO", deptVO);
        }
        return "/dept/update";
    }

    //修改部門資料
    @PostMapping("/update")
    public String update( DeptVO deptVO, Model model) {
        DeptDO deptDO = new DeptDO();//建一個新的 DO，代表要存進資料庫的資料
        BeanUtils.copyProperties(deptVO, deptDO);//將從前端送來的VO資料轉進去剛建立的DO
        DeptDO updated = deptService.update(deptDO);//交給Service 處理
        model.addAttribute("deptVO", transformDeptVO(updated));//回到前端顯示畫面
        return "/dept/listOne";
    }

    // delete(PRG 模式流程)
    @PostMapping("/delete")
    public String delete(Integer deptno) {
        deptService.deleteDept(deptno);
        return "redirect:/dept/listAll";//觸發新的 HTTP GET 請求
    }


    //DO 轉 VO
    private DeptVO transformDeptVO(DeptDO deptDO) {
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(deptDO.getDeptno());
        deptVO.setDname(deptDO.getDname());
        deptVO.setLoc(deptDO.getLoc());
        return deptVO;
    }

    private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOs) {
        return deptDOs.stream()
                .map(this::transformDeptVO)
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
                    empVO.setDeptVO(transformDeptVO(empDO.getDeptDO()));
                    return empVO;
                })
                .collect(Collectors.toList());
    }


}
