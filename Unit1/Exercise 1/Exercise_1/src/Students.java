import java.util.ArrayList;

class Student
{
	private String name;
	private int note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Boolean getAprobado() {
        return note >= 5;
	}

}

public class Students
{
	private ArrayList<Student> studentList= new ArrayList<>();

	// Agrega un nuevo alumno a la lista
	//        
	public void Add(Student student)
	{
		studentList.add(student);
	}

	// Devuelve el alumno que está en la posición num
	//
	public Student get(int num)
	{
		if (num >= 0 && num < studentList.size())
		{
			return studentList.get(num);
		}
		return null;
	}

	// Devuelve la nota media de los alumnos
	//
	public float getAvarage()
	{

			if (studentList.size() == 0)
				return 0;
			else
			{
				float avarage = 0;
				for (int i = 0; i < studentList.size(); i++)
				{
					avarage += studentList.get(i).getNote();
				}
				return (avarage / studentList.size());
			}
		}
	}



