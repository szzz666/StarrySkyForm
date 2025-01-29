package top.szzz666.nukkit_plugin.entity;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    private String title;
    private String content;
    private LinkedHashMap<String, ArrayList<String>> buttons;

    public String toJson() {
        return new Gson().toJson(this);
    }
}
