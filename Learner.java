package Controllers;

import java.util.Objects;

public class Learner {

    private String FName;
    private String SName;
    private int grade;

    public Learner(String FName, String SName, int grade) {
        System.out.println("Learner object made for: " + FName + " " + SName + " " + grade);
        this.FName = FName;
        this.SName = SName;
        this.grade = grade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public boolean equals(Learner test) {
        return (this.FName.equalsIgnoreCase(test.getFName()) && this.SName.equalsIgnoreCase(test.getSName()) && this.grade == (test.getGrade()));
    }

    @Override
    public String toString() {
        return "Learner{" + "FName=" + FName + ", SName=" + SName + ", grade=" + grade + '}';
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
