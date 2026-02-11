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

    /*--------méthodes--------*/
    public static QueryBuilder select(String... columns){
        QueryBuilder builder = new QueryBuilder();
        builder.query.append("SELECT ");
        if (columns.length == 0){
            builder.query.append("*");
        } else {
            builder.query.append(String.join(", ", columns));
        }
        return builder;
    }


}
