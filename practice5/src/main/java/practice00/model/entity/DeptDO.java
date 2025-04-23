package practice00.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Table(name = "dept2")
@Entity
@NamedQuery(name = "dept.all", query = "SELECT dept FROM DeptDO  dept")
public class DeptDO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPTNO", nullable = false)
    private Integer deptno;

	@Column(name= "DNAME")
    private String dname;

	@Column(name = "loc")
    private String loc;
    
	@OneToMany(mappedBy = "deptDO",cascade = CascadeType.REMOVE)
	private List<EmpDO> empDOs;
}
