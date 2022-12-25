package com.nhi.english.MinhTriet;

public class ranking implements Comparable<ranking> {
    int SoCau;
    int Correct_Star;
    int Ear_Finish_Time;

    public ranking(int SoCau, int Correct_Star, int Ear_Finish_Time) {

        this.SoCau = SoCau;
        this.Correct_Star = Correct_Star;
        this.Ear_Finish_Time = Ear_Finish_Time;
    }
    public ranking() {
    }

    public int getEar_Finish_Time() {
        return Ear_Finish_Time;
    }

    public void setEar_Finish_Time(int ear_Finish_Time) {
        Ear_Finish_Time = ear_Finish_Time;
    }

    public int getSoCau() {
        return SoCau;
    }

    public void setSoCau(int soCau) {
        SoCau = soCau;
    }

    public int getCorrect_Star() {
        return Correct_Star;
    }

    public void setCorrect_Star(int correct_Star) {
        Correct_Star = correct_Star;
    }

    @Override
    public int compareTo(ranking ranking) {
        if (SoCau < ranking.SoCau) {
            return 1;
        } else if (SoCau == ranking.SoCau) {
            if(Correct_Star < ranking.Correct_Star)
            {
                return 1;
            }
            else if(Correct_Star == ranking.Correct_Star)
            {
                if (Ear_Finish_Time > ranking.Ear_Finish_Time) return 1;
                else if (Ear_Finish_Time == ranking.Ear_Finish_Time) return 0;
                else return -1;
            }
            else return -1;
        } else return -1;
    }
}