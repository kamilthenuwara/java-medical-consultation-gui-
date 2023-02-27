import com.toedter.calendar.JDateChooser;
import lu.tudor.santec.jtimechooser.JTimeChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


class WestminsterSkinConsultationMnagerGUI extends JFrame{

    public static void main()
    {
        //add jframe
        JFrame frame=new JFrame();
        JPanel panel=new JPanel();
        JPanel tablepanel=new JPanel();
        JPanel pricepanel=new JPanel();
        frame.setSize(550,1000);
        frame.setTitle("westConManGUI");


        List<Doctor> docList= WestminsterSkinConsultationManager.getDoctorList();
        List<Patient> patientList= WestminsterSkinConsultationManager.getPatientList();
        List<Consultation> consultList= WestminsterSkinConsultationManager.getConsultList();

        //add doctor data to table
        Object[][] doctorlist=new Object[10][6];

        for (int x=0;x< docList.size();x++){
                doctorlist[x][0]=docList.get(x).getPerson_name();
                doctorlist[x][1]=docList.get(x).getPerson_Surname();
                doctorlist[x][2]=docList.get(x).getDate_of_birth();
                doctorlist[x][3]=docList.get(x).getMobileNo();
                doctorlist[x][4]=docList.get(x).getMed_licen_no();
                doctorlist[x][5]=docList.get(x).getSpecialization();
            }
        String[] tblheads ={"name","surname","dob","mobileNo","medLicNo","specialization"};


        JTable doctorlisttable=new JTable(doctorlist,tblheads);
        JScrollPane scrollPane = new JScrollPane(doctorlisttable);
        scrollPane.setPreferredSize(new Dimension(400,200));

        //add sorted doctor table
        JButton sort=new JButton();
        sort.setPreferredSize(new Dimension(180,30));
        sort.setText("sort table alphabetically");
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablepanel.remove(scrollPane);
                Doctor tempDoc;
                List<Doctor> doc_list_temp = new ArrayList<>(docList);

                for (int i = 0; i < doc_list_temp.size(); i++) {
                    for (int j = i + 1; j < doc_list_temp.size(); j++) {

                        // to compare one string with other strings
                        if (doc_list_temp.get(i).getPerson_name().compareTo(doc_list_temp.get(j).getPerson_name()) > 0) {
                            // swapping
                            tempDoc = doc_list_temp.get(i);
                            doc_list_temp.set(i, doc_list_temp.get(j));
                            doc_list_temp.set(j, tempDoc);
                        }
                    }
                }
                Object[][] doctorlist=new Object[10][6];

                for (int x=0;x< doc_list_temp.size();x++){
                    doctorlist[x][0]=doc_list_temp.get(x).getPerson_name();
                    doctorlist[x][1]=doc_list_temp.get(x).getPerson_Surname();
                    doctorlist[x][2]=doc_list_temp.get(x).getDate_of_birth();
                    doctorlist[x][3]=doc_list_temp.get(x).getMobileNo();
                    doctorlist[x][4]=doc_list_temp.get(x).getMed_licen_no();
                    doctorlist[x][5]=doc_list_temp.get(x).getSpecialization();
                }
                String[] tblheads ={"name","surname","dob","mobileNo","medLicNo","specialization"};


                JTable doctorlisttable=new JTable(doctorlist,tblheads);
                JScrollPane scrollPane1 = new JScrollPane(doctorlisttable);
                scrollPane1.setPreferredSize(new Dimension(400,200));
                scrollPane1.setLocation(0,0);

                tablepanel.add(scrollPane1);

            }
        });
        //j elements
        JButton add_doc=new JButton();
        add_doc.setPreferredSize(new Dimension(300,30));
        add_doc.setText("Add the doctor to the consultation");

        JLabel blank1=new JLabel(" \n");
        JLabel name=new JLabel("Enter your name :");
        TextField nametxt=new TextField(50);
        nametxt.setColumns(65);

        JLabel surname=new JLabel("Enter your Surname :");
        TextField surnametxt=new TextField(50);
        surnametxt.setColumns(65);
        JLabel dob=new JLabel("Enter your Date of birth :");
        TextField dobtxt=new TextField(50);
        dobtxt.setColumns(65);
        JLabel mobNo=new JLabel("Enter your Mobile No :");
        TextField mobNOtxt=new TextField(50);
        mobNOtxt.setColumns(65);
        JLabel id=new JLabel("Enter your ID No :");
        TextField idNOtxt=new TextField(50);
        idNOtxt.setColumns(65);

        JLabel date=new JLabel("Date :");
        JDateChooser datecal=new JDateChooser();
        datecal.setPreferredSize(new Dimension(200,50));
        JLabel time=new JLabel("Time :");
        JTimeChooser selectTime=new JTimeChooser();
        selectTime.setPreferredSize(new Dimension(250,50));

        JLabel note=new JLabel("Write a note :");
        JTextArea PatientNote=new JTextArea();
        PatientNote.setPreferredSize(new Dimension(250,75));

        JLabel alert=new JLabel("");
        alert.setPreferredSize(new Dimension(550,20));

        JButton ImgUpload=new JButton();
        ImgUpload.setPreferredSize(new Dimension(200,50));
        ImgUpload.setText("Upload Image");
        final String[] selectedImagePath = new String[1];
        ImgUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser browseImageFile = new JFileChooser();
                    //Filter image extensions
                    FileNameExtensionFilter FileExt = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
                    browseImageFile.addChoosableFileFilter(FileExt);
                    int showOpenDialogue = browseImageFile.showOpenDialog(null);

                    if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
                        File selectedImageFile = browseImageFile.getSelectedFile();
                        selectedImagePath[0] = selectedImageFile.getAbsolutePath();
                    }
                } catch (IndexOutOfBoundsException f) {
                    System.out.println("file path not found");
                    alert.setText("alert : file not found");
                }
            }
        });

        JLabel doctor=new JLabel("Doctor :");
        TextField doctordata=new TextField(50);
        doctordata.setColumns(65);

        JLabel docAvailabilty=new JLabel("");

        JLabel pricetxt=new JLabel("Price(\u20AC.)");
        JLabel price=new JLabel("0.00");
        JButton pricebtn=new JButton();
        pricebtn.setPreferredSize(new Dimension(120,30));
        pricebtn.setText("Total price");
        pricebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //set price depend on consultation
                    if (patientList.size()==0){
                        price.setText("15.00");
                    }
                for (Patient tempPatient :patientList){
                    if ((idNOtxt.getText()).equals(tempPatient.patient_id)) {
                        price.setText("25.00");
                    }else{
                        price.setText("15.00");
                    }
                }
                }catch (IndexOutOfBoundsException f){
                    System.out.println("patient list data not found");
                }
            }
        });

        final String[] consultTime = {null};
        add_doc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add doctor to consultation
                try {
                    if (!Objects.equals(selectTime.getFormatedTime().substring(3, 5), "00")) {
                        consultTime[0] = selectTime.getFormatedTime().substring(0, 2) + ":00:00";
                        alert.setText("consultation must be round hour. consultation time set to :"+ consultTime[0]);
                    }else{
                        consultTime[0]=selectTime.getFormatedTime();
                    }

                    int column1=0;
                    int column2=1;
                    int row= doctorlisttable.getSelectedRow();
                    String docName= doctorlisttable.getModel().getValueAt(row, column1).toString();
                    String docsurName= doctorlisttable.getModel().getValueAt(row, column2).toString();
                    Random randDoc = new Random();
                    String docFullName = docName +" "+ docsurName;
                    int turn=0;

                    if (consultList.size()==0){
                        doctordata.setText(docFullName);
                    }

                    for (Consultation tempConsult :consultList){
                        turn=turn+1;
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        if ((tempConsult.consultDoc.equals(docFullName))&&(tempConsult.consultTime.equals(consultTime[0]))&&(tempConsult.consultDate.equals(dateFormat.format(datecal.getDate())))
                        ){
                            int index = randDoc.nextInt(docList.size());
                            Doctor randomDoc=docList.get(index);
                            doctordata.setText(randomDoc.person_name+" "+ randomDoc.person_Surname);
                            docAvailabilty.setText("Doctor you selected is not available,A new doctor assigned.");
                            break;
                        }else if(turn==consultList.size()){
                            doctordata.setText(docFullName);
                        }
                        }
                }catch (IndexOutOfBoundsException f){
                    System.out.println("table data not found");

                }
                }
        });


        JButton Newconsult=new JButton();
        Newconsult.setPreferredSize(new Dimension(180,30));
        Newconsult.setText("New Consultation");
        Newconsult.addActionListener(new ActionListener() {
            //start new consultation
            @Override
            public void actionPerformed(ActionEvent e) {
                WestminsterSkinConsultationMnagerGUI.main();
            }
        });


        JButton consultConfirm=new JButton();
        consultConfirm.setPreferredSize(new Dimension(180,30));
        consultConfirm.setText("Confirm Consultation");


        consultConfirm.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   //add patient to the patient list
                   String tempName=nametxt.getText();
                   String tempSurName=surnametxt.getText();
                   String tempDOB=dobtxt.getText();
                   String tempMobNO=mobNOtxt.getText();
                   String tempIdNo=idNOtxt.getText();
                   Patient p1=new Patient(tempName,tempSurName,tempDOB,tempMobNO,tempIdNo);
                   patientList.add(p1);

                   //add consultation details
                   String pat_note=PatientNote.getText();
                   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                   String consultDate = dateFormat.format(datecal.getDate());
                   Consultation consult=new Consultation(price.getText(),pat_note,consultDate,consultTime[0],doctordata.getText());
                   consultList.add(consult);
                    alert.setText("consultation added");

                   //write data to files
               try {
                   FileWriter PatientFile = new FileWriter("/Users/kamilthenuwara/Desktop/patientsInfo.txt");
                   PatientFile.write(tempName+ "\n");
                   PatientFile.write(tempSurName + "\n");
                   PatientFile.write(tempDOB + "\n");
                   PatientFile.write(tempMobNO + "\n");
                   PatientFile.write(tempIdNo + "\n");

                   PatientFile.close();
               } catch (IOException f) {
                   System.out.println("An error occurred.");
               }

               try {
                   DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                   FileWriter consulationsFile = new FileWriter("/Users/kamilthenuwara/Desktop/consultsInfo.txt");
                   consulationsFile.write(price.getText()+ "\n");
                   consulationsFile.write(PatientNote.getText() + "\n");
                   consulationsFile.write(dateFormat1.format(datecal.getDate()) + "\n");
                   consulationsFile.write(consultTime[0] + "\n");
                   consulationsFile.write(doctordata.getText() + "\n");

                   consulationsFile.close();
               } catch (IOException f) {
                   System.out.println("An error occurred.");
               }

               }catch (InputMismatchException x){
                   System.out.println("Input correct data");
                   alert.setText("alert : fill all the required data correctly");
               }catch (NullPointerException n){
                   System.out.println("fill required fields");
                   alert.setText("alert : fill all the required data correctly");
               }
               }
        });

        //back to home option in view consultation panel
        JButton backHome=new JButton();
        backHome.setPreferredSize(new Dimension(180,30));
        backHome.setText("Home");
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WestminsterSkinConsultationMnagerGUI.main();
            }
        });

        //view consultation panel j elements
        JPanel yourConsultPanel=new JPanel();
        JLabel heading=new JLabel("Your Consultation");
        heading.setPreferredSize(new Dimension(550,20));
        JLabel patientName=new JLabel("Name :");
        patientName.setPreferredSize(new Dimension(550,20));
        JLabel patientNametxt=new JLabel();
        patientNametxt.setPreferredSize(new Dimension(550,20));
        JLabel patientDOB=new JLabel("Date of birth :");
        patientDOB.setPreferredSize(new Dimension(550,20));
        JLabel patientDOBtxt=new JLabel();
        patientDOBtxt.setPreferredSize(new Dimension(550,20));
        JLabel patientmobNo=new JLabel("Mobile No :");
        patientmobNo.setPreferredSize(new Dimension(550,20));
        JLabel patientmobNotxt=new JLabel();
        patientmobNotxt.setPreferredSize(new Dimension(550,20));
        JLabel patientId=new JLabel("ID No :");
        patientId.setPreferredSize(new Dimension(550,20));
        JLabel patientIDtxt=new JLabel();
        patientIDtxt.setPreferredSize(new Dimension(550,20));
        JLabel patientDate=new JLabel("Date :");
        patientDate.setPreferredSize(new Dimension(550,20));
        JLabel patientDatetxt=new JLabel();
        patientDatetxt.setPreferredSize(new Dimension(550,20));
        JLabel patientTime=new JLabel("Time :");
        patientTime.setPreferredSize(new Dimension(550,20));
        JLabel patientTimetxt=new JLabel();
        patientTimetxt.setPreferredSize(new Dimension(550,20));
        JLabel patientDoctor=new JLabel("Doctor Name :");
        patientDoctor.setPreferredSize(new Dimension(550,20));
        JLabel patientDOCtxt=new JLabel();
        patientDOCtxt.setPreferredSize(new Dimension(550,20));
        JLabel patientNote=new JLabel("Patient Note :");
        patientNote.setPreferredSize(new Dimension(550,20));
        JLabel patientNotetxt=new JLabel();
        patientNotetxt.setPreferredSize(new Dimension(550,20));
        JLabel uploadImg=new JLabel("Upload image :");
        uploadImg.setPreferredSize(new Dimension(550,20));
        JLabel uploadImgview=new JLabel();

        //view consultation
        JButton viewConsultation=new JButton();
        viewConsultation.setPreferredSize(new Dimension(180,30));
        viewConsultation.setText("View Your Consultation");
        viewConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                String tempfName=nametxt.getText();
                String templName=surnametxt.getText();
                patientNametxt.setText(tempfName+templName);
                patientDOBtxt.setText(dobtxt.getText());
                patientmobNotxt.setText(mobNOtxt.getText());
                patientIDtxt.setText(idNOtxt.getText());
                patientDatetxt.setText(dateFormat1.format(datecal.getDate()));
                patientTimetxt.setText(consultTime[0]);
                patientDOCtxt.setText(doctordata.getText());
                patientNotetxt.setText(PatientNote.getText());

                //default image when upload image is empty
                if(selectedImagePath[0]==null){
                    selectedImagePath[0]="/Users/kamilthenuwara/Desktop/pro_img.jpeg";
                }
                ImageIcon img = new ImageIcon(selectedImagePath[0]);
                //Resize image to fit jlabel
                int width=200;
                int height=200;
                Image image = img.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);

                uploadImgview.setIcon(new ImageIcon(image));

                frame.remove(panel);

                //view consultation add elements
                yourConsultPanel.add(backHome);
                yourConsultPanel.add(heading);
                yourConsultPanel.add(patientName);
                yourConsultPanel.add(patientNametxt);
                yourConsultPanel.add(patientDOB);
                yourConsultPanel.add(patientDOBtxt);
                yourConsultPanel.add(patientmobNo);
                yourConsultPanel.add(patientmobNotxt);
                yourConsultPanel.add(patientId);
                yourConsultPanel.add(patientIDtxt);
                yourConsultPanel.add(patientDate);
                yourConsultPanel.add(patientDatetxt);
                yourConsultPanel.add(patientTime);
                yourConsultPanel.add(patientTimetxt);
                yourConsultPanel.add(patientDoctor);
                yourConsultPanel.add(patientDOCtxt);
                yourConsultPanel.add(patientNote);
                yourConsultPanel.add(patientNotetxt);
                yourConsultPanel.add(uploadImg);
                yourConsultPanel.add(uploadImgview);

                frame.add(yourConsultPanel);
            }
        });

        //main consultation form add elements
        tablepanel.add(scrollPane);
        panel.add(tablepanel);

        panel.add(sort);
        panel.add(blank1);
        panel.add(add_doc);

        panel.add(name);
        panel.add(nametxt);
        panel.add(surname);
        panel.add(surnametxt);
        panel.add(dob);
        panel.add(dobtxt);
        panel.add(mobNo);
        panel.add(mobNOtxt);
        panel.add(id);
        panel.add(idNOtxt);
        panel.add(date);
        panel.add(datecal);
        panel.add(time);
        panel.add(selectTime);
        panel.add(doctor);
        panel.add(docAvailabilty);
        panel.add(doctordata);
        panel.add(note);
        panel.add(PatientNote);
        panel.add(ImgUpload);

        panel.add(pricepanel);
        pricepanel.add(pricetxt);
        pricepanel.add(price);
        pricepanel.add(pricebtn);

        panel.add(Newconsult);
        panel.add(consultConfirm);
        panel.add(viewConsultation);
        panel.add(alert);

        frame.add(panel);
        frame.setVisible(true);
    }
}