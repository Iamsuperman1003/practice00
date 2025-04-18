package controller;

import model.DeptDO;
import model.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.DeptService;
import service.EmpService;
import jakarta.annotation.PostConstruct;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmpService empService;

    @GetMapping("/")
    public String showIndex(Model model) {
        List<DeptDO> deptDOs = deptService.getAll();
        List<EmpDO> empDOs = empService.getAll();

        model.addAttribute("deptDOs", deptDOs);
        model.addAttribute("empDOs", empDOs);

        return "index";
    }

    @GetMapping("/emp/addForm")
    public String showAddForm(Model model) {
        List<DeptDO> deptDOs = deptService.getAll();
        model.addAttribute("deptDOs", deptDOs);
        return "emp/add";  // 這是新增員工表單頁面的 JSP
    }


}
