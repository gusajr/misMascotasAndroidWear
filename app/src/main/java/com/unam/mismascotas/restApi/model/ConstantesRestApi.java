package com.unam.mismascotas.restApi.model;

public final class ConstantesRestApi {
    public static final String URL_BASE = "https://graph.instagram.com/me/media/";
    public static final String FIELDS = "?fields=id,caption,media_url,username";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    public static final String ACCESS_TOKEN = "IGQVJWT1l5YVVrNzZAyNlFQMWM4ZAWVMN2JCLXc1TjNyWEtfRFl5andJVUUzZAi1MaDZAfYTZAibTVYdk5RWkZANeVRfMG5BcUVadVhrZA2N0ODl4Y1EyWDd6aFpKa3NJZAVA3cDh3RlhRMnNIR09kSm5CdzBiMwZDZD";
    //https://graph.instagram.com/me/media?fields=id,caption,media_url,username,likes&access_token=IGQVJWT1l5YVVrNzZAyNlFQMWM4ZAWVMN2JCLXc1TjNyWEtfRFl5andJVUUzZAi1MaDZAfYTZAibTVYdk5RWkZANeVRfMG5BcUVadVhrZA2N0ODl4Y1EyWDd6aFpKa3NJZAVA3cDh3RlhRMnNIR09kSm5CdzBiMwZDZD

    public static final String GET_MEDIA = FIELDS + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String GET_USER_INFORMATION = URL_BASE + FIELDS + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
}
