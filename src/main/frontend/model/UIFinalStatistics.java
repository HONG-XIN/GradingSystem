package main.frontend.model;

public class UIFinalStatistics {
    String min, max, avg, med, std;

    public UIFinalStatistics(String min, String max, String avg, String med, String std) {
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.med = med;
        this.std = std;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }
}
