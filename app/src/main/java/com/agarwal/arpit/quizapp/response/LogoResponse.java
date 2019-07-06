package com.agarwal.arpit.quizapp.response;

import java.util.ArrayList;
import java.util.List;

public class LogoResponse {
    private List<LogoItem> list = new ArrayList<>();

    public List<LogoItem> getList() {
        return list;
    }

    public void setList(List<LogoItem> list) {
        this.list = list;
    }
}
