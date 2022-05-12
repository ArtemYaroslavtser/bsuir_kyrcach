package com.example.Kyrcach_java_Yaroslavtser.DataTable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageArray {

    private List<List<String>> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;
}

