package in.ineuron.persistence;

import in.ineuron.dto.Student;

public interface IStudentDao {
	
	// operations to be implemented
	public String save(String sname, Integer sage, String saddress);

	public Student searchById(Integer sid);

	public String updateById(Student student);

	public String deleteById(Integer sid);

}
