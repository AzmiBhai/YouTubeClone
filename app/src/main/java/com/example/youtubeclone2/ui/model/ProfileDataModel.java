package com.example.youtubeclone2.ui.model;

import java.util.ArrayList;

public final class ProfileDataModel {

    public static ArrayList<Profile> getProfiles() {
        ArrayList<Profile> profiles = new ArrayList<>();
        profiles.add(new Profile("abc channel","Conent Live","100 views","3 days ago","https://cdn-icons-png.flaticon.com/512/4333/4333609.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEMs3T4oYHJAH25W-zhu0WHPSSQQY3ULJsdQ&usqp=CAU","image"));
        profiles.add(new Profile("abc channel","content","100 views","3 days ago","https://cdn-icons-png.flaticon.com/512/4333/4333609.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEMs3T4oYHJAH25W-zhu0WHPSSQQY3ULJsdQ&usqp=CAU","image"));
        profiles.add(new Profile("abc channel","content","100 views","3 days ago","https://cdn-icons-png.flaticon.com/512/4333/4333609.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEMs3T4oYHJAH25W-zhu0WHPSSQQY3ULJsdQ&usqp=CAU","image"));
        profiles.add(new Profile("abc channel","content","100 views","3 days ago","https://cdn-icons-png.flaticon.com/512/4333/4333609.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEMs3T4oYHJAH25W-zhu0WHPSSQQY3ULJsdQ&usqp=CAU","image"));
        profiles.add(new Profile("abc channel","content","100 views","3 days ago","https://cdn-icons-png.flaticon.com/512/4333/4333609.png",
                "https://www.w3schools.com/html/mov_bbb.mp4","video"));
        return profiles;
    }
}
