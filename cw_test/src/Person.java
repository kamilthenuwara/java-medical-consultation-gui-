public class Person {
    public String person_name;  //person class data variables
    public String person_Surname;
    public String date_of_birth;
    public String mobileNo;

    public Person(String person_name, String person_Surname, String date_of_birth, String mobileNo) {
        this.person_name = person_name;     //constructors
        this.person_Surname = person_Surname;
        this.date_of_birth = date_of_birth;
        this.mobileNo = mobileNo;
    }

    //getters and setters
    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_Surname() {
        return person_Surname;
    }

    public void setPerson_Surname(String person_Surname) {
        this.person_Surname = person_Surname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
