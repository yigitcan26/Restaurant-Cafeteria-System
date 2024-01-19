public class Waitress extends Employee{
	//constructor
    Waitress(String name,String email,String phone_number){
        this.Name = name;
        this.Email = email;
        this.Phone_Number = phone_number;
        this.Salary = 20;
        this.Password = "waitress";
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public String getEmail() {
        return this.Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public String getPassword() {
        return this.Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public float getSalary() {
        return this.Salary;
    }
    public void setSalary(float salary) {
        this.Salary = salary;
    }
    public String getPhone_Number() {
        return this.Phone_Number;
    }
    public void setPhone_Number(String phone_Number) {
        this.Phone_Number = phone_Number;
    }

}
