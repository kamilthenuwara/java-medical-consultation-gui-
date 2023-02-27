
public class Consultation extends WestminsterSkinConsultationManager{
    String consult_cost;    //consultation data
    String notes;
    String consultDate;
    String consultTime;
    String consultDoc;


    public Consultation(String consult_cost, String notes, String consultDate, String consultTime, String consultDoc) {
        this.consult_cost = consult_cost; //constuctors
        this.notes = notes;
        this.consultDate = consultDate;
        this.consultTime = consultTime;
        this.consultDoc = consultDoc;
    }
    //getters and setters

    public String getConsult_cost() {
        return consult_cost;
    }

    public void setConsult_cost(String consult_cost) {
        this.consult_cost = consult_cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(String consultDate) {
        this.consultDate = consultDate;
    }

    public String getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(String consultTime) {
        this.consultTime = consultTime;
    }
    public String getConsultDoc() {
        return consultDoc;
    }

    public void setConsultDoc(String consultDoc) {
        this.consultDoc = consultDoc;
    }

}
