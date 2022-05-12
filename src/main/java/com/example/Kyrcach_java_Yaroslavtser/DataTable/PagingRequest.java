package com.example.Kyrcach_java_Yaroslavtser.DataTable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PagingRequest {
    private int start;
    private int length;
    private int draw;
    private List<Order> order;
    private List<Column> columns;
    private Search search;
}
