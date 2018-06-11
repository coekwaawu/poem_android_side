package com.example.administrator.myapplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class PoemREST {
    public interface PoemService{
        @GET("poems/{poem_id}")
        Call<Poem> getPoem(@Path("poem_id") String poem_id);
    }

    public PoemService GetPoemService(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://121.12.248.112:10086/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PoemService service = retrofit.create(PoemService.class);
        return service;
    }
/*
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://121.12.248.112:10086/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PoemService service = retrofit.create(PoemService.class);
        Call<Poem> call = service.getPoem("ee16df5673bc");
        call.enqueue(new Callback<Poem>() {
            @Override
            public void onResponse(Call<Poem> call, Response<Poem> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Poem> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
*/
}
