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

    public static QueryBuilder insert(String table){
        QueryBuilder builder = new QueryBuilder();
        builder.query.append("INSERT INTO ").append(table);
        return builder;
    }

    public static QueryBuilder update(String table){
        QueryBuilder builder = new QueryBuilder();
        builder.query.append("UPDATE ").append(table);
        return builder;
    }

    public static QueryBuilder delete(){
        QueryBuilder builder = new QueryBuilder();
        builder.query.append("DELETE");
        return builder;
    }

    public QueryBuilder from(String table){
        query.append("FROM ").append(table);
        return this;
    }

    public QueryBuilder columns(String ...columns){
        query.append(" (").append(String.join(", ", columns)).append(")");
        return this;
    }

    public QueryBuilder values(int count){
        query.append(" VALUES (");
        for (int i = 0; i < count; i++){
            query.append("?");
            if (i < count - 1) query.append(", ");
        }
        query.append(")");
        return this;
    }

    public QueryBuilder set(String... columns){
        query.append(" SET ");
        for (int i = 0; i < columns.length; i++){
            query.append(columns[i]).append(" = ?");
            if (i < columns.length - 1) query.append(", ");
        }
        return this;
    }

    public QueryBuilder where(String condition){
        whereConditions.add(condition);
        return this;
    }

    public QueryBuilder orderBy(String... columns){
        for (String columnName : columns){
            orderByColumns.add(columnName);
        }
        return this;
    }

    public String build(){
        if (whereConditions.isEmpty()){
            query.append(" WHERE ");
            query.append(String.join(" AND ", whereConditions));
        }
        if(!orderByColumns.isEmpty()){
            query.append(" ORDER BY ");
            query.append(String.join(", ", orderByColumns));
        }
        return query.toString();
    }

    @Override
    public String toString() {
        return build();
    }
}
