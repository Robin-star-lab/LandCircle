package com.example.landcircle.models;

public class ChiefRequest {

    private String first_name;
    private String second_name;
    private String phone_number;
    private String national_id;
    private String personal_file_number;
    private String stamp_sample;
    private String postal_address;
    private String sublocation_name;

    private String profile_photo;

    public ChiefRequest(String first_name,
                        String second_name,
                        String phone_number,
                        String national_id,
                        String personal_file_number,
                        String stamp_sample,
                        String postal_address,
                        String sublocation_name,
                        String profile_photo) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.phone_number = phone_number;
        this.national_id = national_id;
        this.personal_file_number = personal_file_number;
        this.stamp_sample = stamp_sample;
        this.postal_address = postal_address;
        this.sublocation_name = sublocation_name;
        this.profile_photo = profile_photo;
    }

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

    public String getPersonal_file_number() {
        return personal_file_number;
    }

    public String getStamp_sample() {
        return stamp_sample;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public String getSublocation_name() {
        return sublocation_name;
    }

    public String getProfile_photo() {
        return profile_photo;
    }
}
