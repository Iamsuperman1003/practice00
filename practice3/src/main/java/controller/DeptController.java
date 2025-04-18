package controller;

import model.DeptDO;
import model.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.DeptService;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查詢所有部門
    @GetMapping("/listAll")
    public String listAll(Model model) {
        List<DeptDO> deptList = deptService.getAll();
        model.addAttribute("deptDOs", deptList);
        return "/dept/listAll"; //
    }

    //查詢部門下的員工清單
    @PostMapping("/listEmps_ByDeptno")
    public String listEmpsByDeptno(@RequestParam("deptno") Integer deptno, Model model) {
        List<EmpDO> emps = deptService.getEmpsByDeptno(deptno);
        model.addAttribute("listEmps_ByDeptno", emps);
        model.addAttribute("deptDOs", deptService.getAll());
        return "/dept/listEmpsByDeptno";
    }





    //查詢單一部門（依編號）
    @GetMapping("/getOne_For_Update")
    public String getOneForUpdate(Integer deptno, Model model) {
        DeptDO deptDO = deptService.getOneDept(deptno);
        model.addAttribute("deptDO", deptDO);
        return "/dept/update";
    }

    //修改部門資料
    @PostMapping("/update")
    public String update( DeptDO deptDO, Model model) {
        DeptDO updated = deptService.update(deptDO);
        model.addAttribute("deptDO", updated);
        return "/dept/listOne";
    }

    // delete
    @PostMapping("/delete")
    public String delete(Integer deptno, Model model) {
        deptService.deleteDept(deptno);
        model.addAttribute("deptDOs", deptService.getAll());
        return "/dept/listAll";
    }
}
