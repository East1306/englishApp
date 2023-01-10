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

    public int getEar_Finish_Time() {
        return Ear_Finish_Time;
    }

    public int getSoCau() {
        return SoCau;
    }

    public int getCorrect_Star() {
        if(Correct_Star < 0){
            return Correct_Star = 0;
        }
        return Correct_Star;
    }

    @Override
    public int compareTo(ranking ranking) {
//        for(int i = 0; i < 5; i++){
//            if(Correct_Star > ranking.)
//        }


        if (Correct_Star < ranking.Correct_Star)
        {
            return 1;
        }
        else if (Correct_Star == ranking.Correct_Star)
        {
            if(SoCau < ranking.SoCau)
            {
                return 1;
            }
            else if(SoCau == ranking.SoCau)
            {
//                if (Ear_Finish_Time > ranking.Ear_Finish_Time)
//                {
//                    return 1;
//                }
//                else if (Ear_Finish_Time == ranking.Ear_Finish_Time)
//                {
//                    return 0;
//                }
//                else
//                    return -1;
                return 0;
            }
            else return -1;
        } else return -1;
    }
}
