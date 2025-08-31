package com.example.landcircle.models;

public class LawyerRequest {
    private String first_name;
    private String second_name;
    private String phone_number;
    private String national_id;
    private String practice_number;
    private String work_place;
    private String physical_address;
    private String postal_address;
    private String profile_photo;

    public LawyerRequest(String first_name,
                         String second_name,
                         String phone_number,
                         String national_id,
                         String practice_number,
                         String work_place,
                         String physical_address,
                         String postal_address,
                         String profile_photo) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.phone_number = phone_number;
        this.national_id = national_id;
        this.practice_number = practice_number;
        this.physical_address = physical_address;
        this.work_place = work_place;
        this.postal_address = postal_address;
        this.profile_photo = profile_photo;
    }

    //getters

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getNational_id() {
        return national_id;
    }

    public String getPractice_number() {
        return practice_number;
    }

    public String getPhysical_address() {
        return physical_address;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public String getWork_place() {
        return work_place;
    }

    public String getProfile_photo() {
        return profile_photo;
    }
}
