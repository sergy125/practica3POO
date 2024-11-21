package etsisi.poo.modelo;

import java.util.HashMap;

public class CategoryMap {
    private HashMap<String,Double> statistics;
    public CategoryMap(){
        this.statistics= new HashMap<>();
        setStatistics();
    }
    public void setStatistics(){
        statistics.put("Scored points: ",0.0);
        statistics.put("Matches won: ",0.0);
        statistics.put("Assists points: ",0.0);
        statistics.put("Tournaments won: ",0.0);//HOLA
        statistics.put("Money generated: ",0.0);
    }
    public double getScoredPoints() {
        return statistics.getOrDefault("Scored points: ",0.0);
    }
    public double getMatchesWon(){
        return statistics.getOrDefault("Matches won: ",0.0);
    }
    public double getAssistsPoints(){
        return statistics.getOrDefault("Assists points: ",0.0);
    }
    public double getTournamentsWon(){
        return statistics.getOrDefault("Tournaments won: ",0.0);
    }
    public double getMoneyGenerated(){
        return statistics.getOrDefault("Money generated: ",0.0);
    }

    public void updateStatistics(String category,double value){
        statistics.replace(category,value);
    }

    public HashMap<String, Double> getStatistics() {
        return statistics;
    }
    public String toStringCSV() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < statistics.keySet().size(); i++) {
            String key = (String) statistics.keySet().toArray()[i];
            sb.append(key).append(":").append(statistics.get(key)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    public String toStringJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String key : statistics.keySet()) {
            sb.append("\"").append(key).append("\"").append(":").append(statistics.get(key)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }


}