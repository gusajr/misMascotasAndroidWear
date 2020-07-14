package com.unam.mismascotas.restApi.model;

import com.unam.mismascotas.pojo.Mascota;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointApi {
    //2
    @GET(ConstantesRestApi.GET_MEDIA)
    Call<MascotaResponse> getRecentMedia();
}

// https://graph.instagram.com/me/media?fields=caption,media_url,username,id&access_token=IGQVJWT1l5YVVrNzZAyNlFQMWM4ZAWVMN2JCLXc1TjNyWEtfRFl5andJVUUzZAi1MaDZAfYTZAibTVYdk5RWkZANeVRfMG5BcUVadVhrZA2N0ODl4Y1EyWDd6aFpKa3NJZAVA3cDh3RlhRMnNIR09kSm5CdzBiMwZDZD
// https://graph.instagram.com/me?fields=id,username&access_token=IGQVJWT1l5YVVrNzZAyNlFQMWM4ZAWVMN2JCLXc1TjNyWEtfRFl5andJVUUzZAi1MaDZAfYTZAibTVYdk5RWkZANeVRfMG5BcUVadVhrZA2N0ODl4Y1EyWDd6aFpKa3NJZAVA3cDh3RlhRMnNIR09kSm5CdzBiMwZDZD
