public class Patient extends Person{

    String patient_id;  //patient class data

    public Patient(String person_name, String person_Surname, String date_of_birth, String mobileNo, String patient_id) {
        super(person_name, person_Surname, date_of_birth, mobileNo);    //constructors
        this.patient_id = patient_id;
    }

    //getters and setters
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

}