package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private TextView author_dynasty;
    private TextView content;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    initTextView("73add8822103");
                    return true;
                case R.id.navigation_dashboard:
                    initTextView("6d2dcbe80f96");
                    return true;
                case R.id.navigation_notifications:
                    initTextView("2d6b0c83a500");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        author_dynasty = findViewById(R.id.author_dynasty);
        content = findViewById(R.id.content);
        String poem_id = "632c5beb84eb";
        initTextView(poem_id);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void initTextView(String poem_id){
        PoemREST.PoemService service = ServiceGenerator.createService(PoemREST.PoemService.class);
        Call<Poem> call = service.getPoem(poem_id);
        Call<Poem> clone = call.clone();
        clone.enqueue(new Callback<Poem>() {
            @Override
            public void onResponse(Call<Poem> call, Response<Poem> response) {
                title.setText(response.body().title);
                author_dynasty.setText(response.body().author+"  "+response.body().dynasty);
                content.setText(response.body().content);
            }
            @Override
            public void onFailure(Call<Poem> call, Throwable t) {
            }
        });
    }
}
