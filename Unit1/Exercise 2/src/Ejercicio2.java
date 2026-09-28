import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

    class Person {

        static final String DATE_BIRTH_NOT_VALID = "Date of birth not valid";
        static final String PHONE_NOT_VALID = "Phone not valid";
        static final String SALARY_NOT_VALID = "Salary not valid";

        private String _name;
        private LocalDate _birthDate;

        public String get_name() {
            return _name;
        }

        public void set_name(String _name) {
            this._name = _name;
        }

        public LocalDate getBirthDate() {
            return _birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
            if (birthDate.isAfter(LocalDate.now()))
                throw new IllegalArgumentException(DATE_BIRTH_NOT_VALID);
            else
                this._birthDate = birthDate;
        }

        public int getEdad() {
            return LocalDate.now().getYear() - _birthDate.getYear();
        }
    }

    class Client extends Person
    {
        //
        private String _phone;
        private Set<Company> _isClientOf = new HashSet<>();

        public String getPhone() {
            return _phone;
        }

        public void setPhone(String phone) {
            String phonePatron = "^((\\+|00)\\d{2,3})?\\d{9}$";
            if(Pattern.matches(phonePatron, phone))
            this._phone = phone;
            else
                throw new IllegalArgumentException(PHONE_NOT_VALID);
        }
    }

    class Employee extends Person
	{
		private double _grossSalary;

        public double getSueldoBruto() {
            return _grossSalary;
        }

        public void setSueldoBruto(double sueldoBruto) {
            if(sueldoBruto < 0)
                throw new IllegalArgumentException(SALARY_NOT_VALID);
            else{
            this._grossSalary = sueldoBruto;
            }
        }
	}
	
	class Executive extends Employee
    {
        //
        private String _category;
        private Set<Employee> _supervises = new HashSet<>();

        public String getCategory() {
            return _category;
        }

        public void setCategory(String category) {
            this._category = category;
        }

        public int getSubordinates() {
            return _supervises.size();
        }
    }
	
	//
    class Company
    {
        //
        private String _name;
        private Set<Employee> _template = new HashSet<>();
        private Set<Client> _clientPortfolio = new HashSet<>();

        public String getName() {
            return _name;
        }

        public void setName(String name) {
            this._name = name;
        }

        public int getTotalClients() {
            return _clientPortfolio.size();
        }

        public int getTotalEmployees() {
            return _template.size();
        }
    }

    void main() {
    }