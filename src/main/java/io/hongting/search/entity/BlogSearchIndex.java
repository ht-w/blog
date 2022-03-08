package io.hongting.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hongting
 * @create 2021 12 20 7:56 PM
 */
@Data
@Document(indexName = "blog")
public class BlogSearchIndex {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String description;


    @Field(type= FieldType.Integer)
    private Integer view;

    @Field(type = FieldType.Integer)
    private Integer commentCount = 0;

    @Field(type = FieldType.Text)
    private String category;

    @Field (type = FieldType.Date, format = DateFormat.date_optional_time)
    private Date createTime;

    @Field (type = FieldType.Date, format = DateFormat.date_optional_time)
    private Date updateTime ;






}