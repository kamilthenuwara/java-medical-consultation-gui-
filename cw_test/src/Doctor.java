
public class Doctor extends Person {

    String med_licen_no;    //doctor class data
    String specialization;


    public Doctor(String person_name, String person_Surname, String date_of_birth, String mobileNo, String med_licen_no,String specialization) {
        super(person_name, person_Surname, date_of_birth, mobileNo); //constructors
        this.med_licen_no = med_licen_no;
        this.specialization=specialization;
    }
        //getters and setters

    public String getMed_licen_no() {
        return med_licen_no;
    }

    public void setMed_licen_no(String med_licen_no) {
        this.med_licen_no = med_licen_no;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
