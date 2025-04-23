package practice00.service;

import java.util.List;
import java.util.Optional;

import practice00.model.entity.EmpDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmpService {

    EmpDO addEmp(EmpDO empDO);

    EmpDO updateEmp(EmpDO empDO);

    void deleteEmp(Integer empno);

    Optional<EmpDO> getOneEmp(Integer empno);

    List<EmpDO> getAll();

    Page<EmpDO> getAllPages(Pageable pageable);

}
