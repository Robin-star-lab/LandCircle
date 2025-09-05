package com.example.landcircle.models;
import com.example.landcircle.models.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.http.Part;

public interface ApiService {
    @POST("/users") // Matches Node.js route
    Call<Void> signup(@Body User user) ;
    @POST("/users/signin")
    Call<LogInResponse> signIn(@Body SignInRequest request);

    @Multipart
    @POST("/lawyers")
    Call<Void> registerLawyer(
            @Header("Authorization") String token,
            @Part("first_name") RequestBody firstName,
            @Part("second_name") RequestBody secondName,
            @Part("phone_number") RequestBody phoneNumber,
            @Part("national_id") RequestBody nationalId,
            @Part("practice_number") RequestBody practiceNumber,
            @Part("work_place") RequestBody workPlace,
            @Part("physical_address") RequestBody physicalAddress,
            @Part("postal_address") RequestBody postalAddress,
            @Part MultipartBody.Part profile_photo
    );
    @Multipart
    @POST("/chiefs")
    Call<Void> registerChief(
            @Header("Authorization") String token,
            @Part("first_name") RequestBody firstName,
            @Part("second_name") RequestBody secondName,
            @Part("phone_number") RequestBody phone,
            @Part("national_id") RequestBody nationalId,
            @Part("personal_file_number") RequestBody personalFile,
            @Part("stamp_sample") RequestBody stampSample,
            @Part("postal_address") RequestBody postalAddress,
            @Part("sublocation_name") RequestBody sublocationName,
            @Part MultipartBody.Part profile_photo
    );
    @Multipart
    @POST("/planners")
    Call<Void> registerPlanners(
            @Header("Authorization") String token,
            @Part("first_name") RequestBody firstName,
            @Part("second_name") RequestBody secondName,
            @Part("phone_number") RequestBody phoneNumber,
            @Part("national_id") RequestBody nationalId,
            @Part("physical_address_number") RequestBody physicalAddress,
            @Part("postal_address") RequestBody postalAddress,
            @Part("pprb_reggistration") RequestBody pprb,
            @Part("business_name") RequestBody businessName,
            @Part MultipartBody.Part profile_photo
    );
    @Multipart
    @POST("/surveyors")
    Call<Void> registerSurveyors(
            @Header("Authorization") String token,
            @Part("first_name") RequestBody firstName,
            @Part("second_name") RequestBody secondName,
            @Part("phone_number") RequestBody phoneNumber,
            @Part("national_id") RequestBody nationalId,
            @Part("postal_address") RequestBody postalAddress,
            @Part("physical_address") RequestBody physicalAddress,
            @Part("lsb_reg_no") RequestBody lsbRegNumber,
            @Part("isk_membership") RequestBody iskMembershipNumber,
            @Part MultipartBody.Part profile_photo
    );

    @Multipart
    @POST("/valuers")
    Call<Void> registerValuer(
            @Header("Authorization") String token,
            @Part("first_name") RequestBody firstName,
            @Part("second_name") RequestBody secondName,
            @Part("phone_number") RequestBody phone,
            @Part("national_id") RequestBody nationalId,
            @Part("vrb_number") RequestBody vrbNumber,
            @Part("isk_membership") RequestBody iskMembershipNumber,
            @Part("physical_address") RequestBody physicalAddress,
            @Part("postal_address") RequestBody postalAddress,
            @Part MultipartBody.Part profile_photo
    );




}
