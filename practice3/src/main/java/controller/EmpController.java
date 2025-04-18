package controller;

import model.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.EmpService;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    // 查全部員工
    @GetMapping("/listAll")
    public String listAll(Model model) {
        List<EmpDO> list = empService.getAll();
        model.addAttribute("empDOs", list);
        return "/emp/listAll";
    }

    // 查單筆員工
    @GetMapping("/getOne")
    public String getOne(@RequestParam("empno") Integer empno, Model model) {
        EmpDO emp = empService.getOneEmp(empno);
        model.addAttribute("empDO", emp);
        return "/emp/listOne";
    }




    // 新增員工
    @PostMapping("/add")
    public String add(@ModelAttribute("empDO") EmpDO empDO, Model model) {
        // 確保 empDO 中的資料已正確接收
        System.out.println(empDO);  // 確認是否正確接收了表單資料

        EmpDO newEmp = empService.addEmp(empDO);  // 新增員工
        model.addAttribute("empDO", newEmp);
        return "/emp/add";  // 返回新增結果頁面
    }


    // 修改員工
    @PostMapping("/update")
    public String update(EmpDO empDO, Model model) {
        EmpDO updated = empService.updateEmp(empDO);
        model.addAttribute("empDO", updated);
        return "/emp/listOne";
    }

    // 刪除員工
    @PostMapping("/delete")
    public String delete(Integer empno, Model model) {
        empService.deleteEmp(empno);
        List<EmpDO> list = empService.getAll();
        model.addAttribute("empDOs", list);
        return "/emp/listAll";
    }
}
