package practice00.service.impl;

import java.util.List;
import java.util.Optional;


import practice00.model.entity.DeptDO;
import practice00.model.entity.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice00.repository.DeptRepository;
import practice00.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;


    @Override
    public List<DeptDO> getAll() {
        return deptRepository.findAll();
    }

    @Override
    public Optional<DeptDO> getOneDept(Integer deptno){
        return deptRepository.findById(deptno);
    }

    @Override
    @Transactional
    public DeptDO update(DeptDO deptDO) {
        DeptDO daoDo = deptRepository.save(deptDO);
        if (daoDo != null) {
            daoDo.setDname(deptDO.getDname());
            daoDo.setLoc(deptDO.getLoc());
            deptRepository.save(daoDo);
        }
        return daoDo;
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        DeptDO deptDO = deptRepository.getOneDeptWithEmps(deptno);
        return deptDO.getEmpDOs();
    }

    @Override
    @Transactional
    public void deleteDept(Integer deptno) {
        deptRepository.deleteById(deptno);
    }

    @Override
    public Page<DeptDO> getAllPages(Pageable pageable) {
        return deptRepository.findAll(pageable);
    }
}
