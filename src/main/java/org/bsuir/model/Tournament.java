package org.bsuir.model;

public class Tournament {
    private String tournamentName;
    private String sportName;
    private String fullName;
    private DateManager tournamentDate;
    private double prize;
    private double income;

    public Tournament(String tournamentName, String sportName, String fullName, DateManager tournamentDate, int prize) {
        this.tournamentName = tournamentName;
        this.sportName = sportName;
        this.fullName = fullName;
        this.tournamentDate = tournamentDate;
        this.prize = prize;
        this.income = prize*0.6;
    }

    public Tournament() {

    }

    public String getFullName() {
        return fullName;
    }

    public DateManager getTournamentDate() {
        return tournamentDate;
    }

    public double getIncome() {
        return income;
    }

    public double getPrize() {
        return prize;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIncome(double income) { this.income = income; }

    public void setTournamentDate(DateManager tournamentDate) {
        this.tournamentDate = tournamentDate;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
