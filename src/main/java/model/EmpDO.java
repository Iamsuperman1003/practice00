package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "emp2")
@Entity
@NamedQuery(name = "emp.all", query = "select emp from EmpDO emp")
public class EmpDO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "EMPNO", nullable = false)
    private Integer empno;

	@Column(name = "ENAME")
    private String ename;

	@Column(name = "JOB")
    private String job;

	@Column(name = "HIREDATE")
    private LocalDate hiredate;

	@Column(name = "SAL")
    private Double sal;

	@Column(name = "comm")
    private Double comm;

	@ManyToOne
	@JoinColumn(name = "DEPTNO")
    private DeptDO deptDO;


}
