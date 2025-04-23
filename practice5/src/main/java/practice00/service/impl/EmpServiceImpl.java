package practice00.service.impl;

import java.util.List;
import java.util.Optional;


import practice00.model.entity.EmpDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice00.repository.EmpRepository;
import practice00.service.EmpService;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {


    @Autowired
    private EmpRepository empRepository;

    @Override
    public EmpDO addEmp(EmpDO empDO) {
        return empRepository.save(empDO);
    }

    @Override
    public EmpDO updateEmp(EmpDO empDO) {
        EmpDO empDO1 = empRepository.save(empDO);
        if (empDO1 != null) {
            empDO1.setEname(empDO.getEname());
            empDO1.setJob(empDO.getJob());
            empDO1.setHiredate(empDO.getHiredate());
            empDO1.setSal(empDO.getSal());
            empDO1.setComm(empDO.getComm());
            empRepository.save(empDO1);
        }
        return empDO1;
    }

    @Override
    public void deleteEmp(Integer empno) {
        empRepository.deleteById(empno);
    }

    @Override
    public Optional<EmpDO> getOneEmp(Integer empno) {
        return empRepository.findById(empno);
    }

    @Override
    public List<EmpDO> getAll() {
        return empRepository.findAll();
    }

    @Override
    public Page<EmpDO> getAllPages(Pageable pageable) {
        return empRepository.findAll(pageable);
    }
}
