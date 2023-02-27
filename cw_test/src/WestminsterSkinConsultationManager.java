import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors


public class WestminsterSkinConsultationManager implements SkinConsultationManager{

    public static List<Doctor> getDoctorList() {
        return doctorList;
    }

    static List<Doctor> doctorList = new ArrayList<>(10);   //doctors list

    public static List<Patient> getPatientList() {
        return patientList;
    }

    static List<Patient> patientList = new ArrayList<>(10); //patients list

    public static List<Consultation> getConsultList() {
        return ConsultList;
    }

    static List<Consultation> ConsultList = new ArrayList<>(10);    //consultations list

    public static void main(String[] args) {
        try{
            //retrieve data from file to doctor list
            File myObj = new File("/Users/kamilthenuwara/Desktop/doctorsInfo.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String name = myReader.nextLine();
                String sur_name = myReader.nextLine();
                String dob = myReader.nextLine();
                String mobile_no = myReader.nextLine();
                String med_licen_no = myReader.nextLine();
                String specialization = myReader.nextLine();
                Doctor doc1=new Doctor(name,sur_name,dob,mobile_no,med_licen_no,specialization);
                doctorList.add(doc1);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        try{
            //retrieve data from file to patients list
            File myObj = new File("/Users/kamilthenuwara/Desktop/patientsInfo.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String name = myReader.nextLine();
                String sur_name = myReader.nextLine();
                String dob = myReader.nextLine();
                String mobile_no = myReader.nextLine();
                String idNo = myReader.nextLine();
                Patient pat1=new Patient(name,sur_name,dob,mobile_no,idNo);
                patientList.add(pat1);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }


        try {
            //retrieve data from file to consultation list
            File myObj = new File("/Users/kamilthenuwara/Desktop/consultsInfo.txt");
            Scanner myReader = new Scanner(myObj);


            while (myReader.hasNextLine()) {
                String price = myReader.nextLine();
                String patientNote = myReader.nextLine();
                String date = myReader.nextLine();
                String time = myReader.nextLine();
                String docName = myReader.nextLine();
                Consultation con1 = new Consultation(price, patientNote, date, time, docName);
                ConsultList.add(con1);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }


        while (true) {
            //console menu
            Scanner menuOption = new Scanner(System.in);
            System.out.println("""
                    Enter menu option.
                    Enter a to add doctor.
                    Enter d to delete doctor.
                    Enter p to print doctor list.
                    Enter s to save doctor list.
                    Enter g to open consultation manager.""");
            String optionInput = menuOption.next();
            switch (optionInput) {
                case "a":
                    addDoctor();

                    break;
                case "d":
                    DeleteDoctor();
                    break;
                case "p":
                    printDoctors();

                    break;

                case "s":
                    saveDoctors();

                    break;
                case "g":
                    WestminsterSkinConsultationMnagerGUI.main();
                    break;

                default:
                    System.out.println("Wrong Input Entered");
            }
        }
    }
    public static void addDoctor() {//add doctor function
        try {
            //doctor details input
            Scanner menuOption = new Scanner(System.in);
            System.out.println("Enter name:");
            String doctorName = menuOption.next();
            System.out.println("Enter surname:");
            String doctorSurname = menuOption.next();
            System.out.println("Enter date_of_birth");
            String doctorDate_of_birth = menuOption.next();
            System.out.println("Enter mobileNo");
            int doctorMobileNoint = menuOption.nextInt();
            System.out.println("Enter med_licen_no");
            int doctorMed_licen_noInt = menuOption.nextInt();
            System.out.println("Enter specialization");
            String doctorSpecialization = menuOption.next();
            String doctorMobileNo = String.valueOf(doctorMobileNoint);
            String doctorMed_licen_no = String.valueOf(doctorMed_licen_noInt);
            Doctor d1 = new Doctor(doctorName, doctorSurname, doctorDate_of_birth, doctorMobileNo, doctorMed_licen_no, doctorSpecialization);
            doctorList.add(d1);
            System.out.println("Doctor added.");
        }catch (InputMismatchException e){
            System.out.println("Input type error");
        }
    }
    public static void DeleteDoctor() { //delete doctorfunction
        try{
            Scanner del_input = new Scanner(System.in);
            System.out.println("Enter med_licen_no");
            int doctorMed_licen_noInt = del_input.nextInt(); //input medical licence no to delete doctor
            String doctorMed_licen_no1 = String.valueOf(doctorMed_licen_noInt);
            for (Doctor doc : doctorList) {
                if (doctorMed_licen_no1.equals(doc.med_licen_no)) {
                    System.out.println("details of deleted doctor\n" + "Name : " + doc.person_name + "\n" + "Surname : " + doc.person_Surname + "\n" + "Date of birth : " + doc.date_of_birth + "\n" + "mobile no : " + doc.mobileNo + "\n" + "specialization : " + doc.specialization + "\n is removed");
                    doctorList.remove(doc);
                    break;
                }

            }}catch (InputMismatchException e){
            System.out.println("Input type error");
        }
    }

    public static void printDoctors() {
        Doctor tempDoc;
        List<Doctor> doc_list_temp = new ArrayList<Doctor>(doctorList);

        for (int i = 0; i < doc_list_temp.size(); i++) {
            for (int j = i + 1; j < doc_list_temp.size(); j++) {

                // to compare one string with other strings
                if (doc_list_temp.get(i).getPerson_Surname().compareTo(doc_list_temp.get(j).getPerson_Surname()) > 0) {
                    // sorting alphabetically
                    tempDoc = doc_list_temp.get(i);
                    doc_list_temp.set(i, doc_list_temp.get(j));
                    doc_list_temp.set(j, tempDoc);
                }
            }
        }
        for (Doctor doctor : doc_list_temp) {   //print sorted list
            System.out.println(doctor.getPerson_name());
            System.out.println(doctor.getPerson_Surname());
            System.out.println(doctor.getDate_of_birth());
            System.out.println(doctor.getMobileNo());
            System.out.println(doctor.getMed_licen_no());
            System.out.println(doctor.getSpecialization());
            System.out.println(" ");
        }
    }

    public static void saveDoctors() {
        try {
            //save doctor data to file
            FileWriter docFile = new FileWriter("/Users/kamilthenuwara/Desktop/doctorsInfo.txt");
            for (Doctor doctor : doctorList) {
                docFile.write(doctor.getPerson_name() + "\n");
                docFile.write(doctor.getPerson_Surname() + "\n");
                docFile.write(doctor.getDate_of_birth() + "\n");
                docFile.write(doctor.getMobileNo() + "\n");
                docFile.write(doctor.getMed_licen_no() + "\n");
                docFile.write(doctor.getSpecialization() + "\n");
            }
            docFile.close();
            System.out.println("Doctors saved.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}