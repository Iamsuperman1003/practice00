package practice00.controller;

import practice00.model.VO.DeptVO;
import practice00.model.VO.EmpVO;
import practice00.model.entity.DeptDO;
import practice00.model.entity.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice00.service.DeptService;
import practice00.service.EmpService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @Autowired
    private DeptService deptService;
    // 查全部員工(提供分頁)
    @GetMapping("/listAll")
    public String listAll(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmpDO> empPage = empService.getAllPages(pageable); // 分頁查詢
        List<EmpVO> empVOs = transformEmpVOs(empPage.getContent());

        model.addAttribute("empVOs", empVOs);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", empPage.getTotalPages());
        return "/emp/listAll";
    }


    // 查單筆員工
    @GetMapping("/getOne")
    public String getOne(Integer empno, Model model) {
        Optional<EmpDO> emp = empService.getOneEmp(empno);
        if (emp.isPresent()) {
            EmpVO empVO = transformEmpVO(emp.get());
            model.addAttribute("empVO", empVO);
        }
        return "/emp/listOne";
    }

    // 新增員工
    @GetMapping("/add")
    public String showAddPage(Model model) {
        EmpVO empVO = new EmpVO();
        model.addAttribute("empVO", empVO);
        model.addAttribute("deptVOs", transformDeptVOs(deptService.getAll()));
        return "emp/add";
    }

    // 新增員工
    @PostMapping("/insert")
    public String add(EmpVO empVO, RedirectAttributes redirectAttrs) {
        EmpDO empDO = transformEmpDO(empVO);
        empService.addEmp(empDO);
        redirectAttrs.addFlashAttribute("message", "新增成功！");//提示訊息
        return "redirect:/emp/listAll";
    }

    // 修改員工(接收查詢單筆資料 → 顯示修改表單)
    @PostMapping("/getOne_For_Update")
    public String showUpdatePage(Integer empno, Model model) {
        Optional<EmpDO> oneEmp = empService.getOneEmp(empno);
        if (oneEmp.isPresent()) {
            EmpDO empDO = oneEmp.get();
            EmpVO empVO = transformEmpVO(empDO);
            model.addAttribute("empVO", empVO);
            model.addAttribute("deptVOs", transformDeptVOs(deptService.getAll()));
        }
        return "emp/update";
    }

    // 修改員工(接收表單資料 → 執行修改)
    @PostMapping("/update")
    public String update(EmpVO empVO, Model model) {
        EmpDO empDO = transformEmpDO(empVO);
        EmpDO updated = empService.updateEmp(empDO);
        model.addAttribute("empVO", transformEmpVO(updated));
        return "/emp/listOne";
    }


    // 刪除員工
    @PostMapping("/delete")
    public String delete(Integer empno, RedirectAttributes redirectAttrs) {
        empService.deleteEmp(empno);
        redirectAttrs.addFlashAttribute("message", "刪除成功！");//提示訊息
        return "redirect:/emp/listAll";
    }


    //將多筆 DeptDO 轉換為 DeptVO(顯示部門列表)
    private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOs) {
        return deptDOs.stream()
                .map(this::transformDeptVO)
                .collect(Collectors.toList());
    }

    //將單筆 DeptDO 轉為 DeptVO(顯示部門詳細資料或轉換員工內嵌部門)
    private EmpVO transformEmpVO(EmpDO empDO) {
        EmpVO empVO = new EmpVO();
        empVO.setEmpno(empDO.getEmpno());
        empVO.setEname(empDO.getEname());
        empVO.setJob(empDO.getJob());
        empVO.setHiredate(format(empDO.getHiredate()));
        empVO.setSalary(empDO.getSal());
        empVO.setComm(empDO.getComm());
        empVO.setDeptVO(transformDeptVO(empDO.getDeptDO()));
        return empVO;
    }

    //顯示員工清單頁面
    private List<EmpVO> transformEmpVOs(List<EmpDO> empDOs) {
        return empDOs.stream()
                .map(this::transformEmpVO)
                .collect(Collectors.toList());
    }



    //將單筆 EmpDO 轉為 EmpVO(顯示單筆員工或員工清單)
    private EmpDO transformEmpDO(EmpVO empVO) {
        EmpDO empDO = new EmpDO();
        empDO.setEmpno(empVO.getEmpno());
        empDO.setEname(empVO.getEname());
        empDO.setJob(empVO.getJob());
        empDO.setHiredate(parse(empVO.getHiredate()));
        empDO.setSal(empVO.getSalary());
        empDO.setComm(empVO.getComm());
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(empVO.getDeptno());
        empDO.setDeptDO(deptDO);
        return empDO;
    }


    //將單筆 EmpVO 轉為 EmpDO(表單送出更新、新增員工資料時使用)
    private DeptVO transformDeptVO(DeptDO deptDO) {
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(deptDO.getDeptno());
        deptVO.setDname(deptDO.getDname());
        deptVO.setLoc(deptDO.getLoc());
        return deptVO;
    }

    //將日期格式化成字串 (yyyy-MM-dd)，顯示在畫面上給使用者看
    private String format(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDate);
    }

    //將字串轉回 LocalDate，從前端表單接收字串轉為 LocalDate
    private LocalDate parse(String LocalDataString) {
        return LocalDate.parse(LocalDataString);
    }
}
