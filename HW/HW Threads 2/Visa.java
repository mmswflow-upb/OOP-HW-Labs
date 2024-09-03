public class Visa extends Candidate {

    private String visaId;

    Visa(Embassy embassy, String firstName, String lastName, int age, String passportNumber, int educationLevel,
            int numberOfApplications, String visaId) {
        super(embassy, firstName, lastName, age, passportNumber, educationLevel, numberOfApplications);

        this.visaId = visaId;
    }

    public void setVisaId(String visaId) {
        this.visaId = visaId;
    }

    public String getVisaId() {
        return visaId;
    }
}
