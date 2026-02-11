package dao.builder;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private StringBuilder query;
    private List<String> whereConditions;
    private List<String> orderByColumns;

    private QueryBuilder(){
        this.query = new StringBuilder();
        this.whereConditions = new ArrayList<>();
        this.orderByColumns = new ArrayList<>();
    }
    

}
